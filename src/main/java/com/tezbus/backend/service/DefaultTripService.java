package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateTripDto;
import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.dto.UpdateTripDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.entity.Driver;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.mapper.TripMapper;
import com.tezbus.backend.pageable.TripSearchRequest;
import com.tezbus.backend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultTripService implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private TripMapper tripMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ReadTripDto> findTrips(TripSearchRequest tripSearchRequest, Pageable pageable) {
        City departureCity = cityService.getById(tripSearchRequest.getDepartureCity());
        City destinationCity = cityService.getById(tripSearchRequest.getDestinationCity());

        List<Address> departuresAddresses = addressService.getAllByCity(departureCity);
        List<Address> destinationAddresses = addressService.getAllByCity(destinationCity);

        ZonedDateTime[] fromToDates = getFromToDates(tripSearchRequest.getDate(), departureCity.getTimeZone());

        List<Trip> trips = tripRepository.findByDepartureAddressInAndDestinationAddressInAndStartTimeGreaterThanEqualAndStartTimeLessThanEqual(
                departuresAddresses,
                destinationAddresses,
                fromToDates[0],
                fromToDates[1],
                pageable
        );

        List<Trip> existingTripsWithSeats = trips.stream()
                .filter(it -> !it.isDeleted())
                .filter(it -> it.getAvailablePassengersCount() >= tripSearchRequest.getSeatsCount())
                .collect(Collectors.toList());

        return new PageImpl<>((existingTripsWithSeats.stream()
                .sorted(Comparator.comparing(Trip::getStartTime))
                .map(it -> tripMapper.toReadTripDto(it))
                .collect(Collectors.toList())));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadTripDto> getAllByDriverId(UUID driverId, Pageable pageable) {
        List<Trip> trips = tripRepository.findByDriverId(driverId, pageable);

        return new PageImpl<>(trips.stream().map(trip -> tripMapper.toReadTripDto(trip)).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public ReadTripDto getById(UUID id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        return tripMapper.toReadTripDto(trip);
    }

    @Override
    @Transactional
    public ReadTripDto create(CreateTripDto createTripDto) {
        Address departureAddress = addressService.getById(createTripDto.getDepartureAddressId());
        Address destinationAddress = addressService.getById(createTripDto.getDestinationAddressId());
        Driver driver = driverService.getById(createTripDto.getDriverId());

        Trip trip = new Trip();
        trip.setDepartureAddress(departureAddress);
        trip.setDestinationAddress(destinationAddress);
        trip.setDriver(driver);
        trip.setDuration(calculateDuration(trip));
        trip.setStartTime(setTimeZone(trip.getStartTime(), "UTC"));
        trip.setEndTime(setTimeZone(trip.getEndTime(), "UTC"));
        trip.setAvailablePassengersCount(createTripDto.getAvailablePassengersCount());
        trip.setPassengersCapacity(createTripDto.getPassengersCapacity());
        trip.setCaption(createTripDto.getCaption());
        trip.setPrice(createTripDto.getPrice());
        trip.setTransportType(createTripDto.getTransportType());
        trip.setCreatedAt(ZonedDateTime.now());

        Trip savedTrip = tripRepository.save(trip);

        return tripMapper.toReadTripDto(savedTrip);
    }

    @Override
    @Transactional
    public ReadTripDto delete(UUID id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        trip.setDeleted(true);

        Trip updatedTrip = tripRepository.save(trip);

        return tripMapper.toReadTripDto(updatedTrip);
    }

    @Override
    @Transactional
    public ReadTripDto update(UUID id, UpdateTripDto updateTripDto) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        Address departureAddress = addressService.getById(updateTripDto.getDepartureAddressId());
        Address destinationAddress = addressService.getById(updateTripDto.getDestinationAddressId());

        trip.setDepartureAddress(departureAddress);
        trip.setDestinationAddress(destinationAddress);
        trip.setDuration(calculateDuration(trip));
        trip.setStartTime(setTimeZone(trip.getStartTime(), "UTC"));
        trip.setEndTime(setTimeZone(trip.getEndTime(), "UTC"));
        trip.setAvailablePassengersCount(updateTripDto.getAvailablePassengersCount());
        trip.setPassengersCapacity(updateTripDto.getPassengersCapacity());
        trip.setCaption(updateTripDto.getCaption());
        trip.setPrice(updateTripDto.getPrice());
        trip.setTransportType(updateTripDto.getTransportType());
        trip.setModifiedAt(ZonedDateTime.now());

        Trip updatedTrip = tripRepository.save(trip);

        return tripMapper.toReadTripDto(updatedTrip);
    }

    private Long calculateDuration(Trip trip) {
        return ChronoUnit.MINUTES.between(trip.getStartTime(), trip.getEndTime());
    }

    private ZonedDateTime setTimeZone(ZonedDateTime dateTime, String zoneId) {
        return dateTime.withZoneSameInstant(ZoneId.of(zoneId));
    }

    private ZonedDateTime[] getFromToDates(LocalDate localDate, String timeZone) {
        ZonedDateTime[] dates = new ZonedDateTime[2];
        ZonedDateTime cityTimeZone = ZonedDateTime.of(localDate, LocalTime.parse("00:00:00"), ZoneId.of(timeZone));

        dates[0] = cityTimeZone.withZoneSameInstant(ZoneId.of("UTC"));
        dates[1] = cityTimeZone.withZoneSameInstant(ZoneId.of("UTC")).plusDays(1);

        return dates;
    }

}
