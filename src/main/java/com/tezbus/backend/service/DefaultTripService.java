package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateTripDto;
import com.tezbus.backend.dto.GenerateTripDto;
import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.dto.SendPassengerDetailsDto;
import com.tezbus.backend.dto.UpdateTripDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.entity.User;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    private UserService userService;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private SmsMessageSenderService smsMessageSenderService;

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
    public Page<ReadTripDto> getAllByUserId(String userId, Pageable pageable) {
        User user = userService.getById(userId);

        List<Trip> trips = tripRepository.findByUser(user, pageable);

        return new PageImpl<>(trips.stream().map(trip -> tripMapper.toReadTripDto(trip)).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public ReadTripDto getById(String id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        return tripMapper.toReadTripDto(trip);
    }

    @Override
    @Transactional
    public ReadTripDto create(CreateTripDto createTripDto) {
        Address departureAddress = addressService.getById(createTripDto.getDepartureAddressId());
        Address destinationAddress = addressService.getById(createTripDto.getDestinationAddressId());
        User user = userService.getById(createTripDto.getUserId());

        ZonedDateTime startTime = createTripDto.getStartTime().withZoneSameInstant(ZoneId.of(departureAddress.getCity().getTimeZone()));
        ZonedDateTime endTime = createTripDto.getEndTime().withZoneSameInstant(ZoneId.of(destinationAddress.getCity().getTimeZone()));

        Trip trip = new Trip();
        trip.setDepartureAddress(departureAddress);
        trip.setDestinationAddress(destinationAddress);
        trip.setUser(user);
        trip.setStartTime(setTimeZone(startTime, "UTC"));
        trip.setEndTime(setTimeZone(endTime, "UTC"));
        trip.setDuration(calculateDuration(trip));
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
    public void sendPassengerDetails(String id, SendPassengerDetailsDto sendPassengerDetailsDto) {
        User user = userService.getById(sendPassengerDetailsDto.getUserId());
        Trip trip = getEntityById(id);

        smsMessageSenderService.sendOnPassengerReservation(trip, sendPassengerDetailsDto, user);
    }

    @Override
    @Transactional
    public ReadTripDto delete(String id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        trip.setDeleted(true);

        Trip updatedTrip = tripRepository.save(trip);

        return tripMapper.toReadTripDto(updatedTrip);
    }

    @Override
    @Transactional
    public ReadTripDto update(String id, UpdateTripDto updateTripDto) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        Trip trip = optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));

        Address departureAddress = addressService.getById(updateTripDto.getDepartureAddressId());
        Address destinationAddress = addressService.getById(updateTripDto.getDestinationAddressId());

        ZonedDateTime startTime = updateTripDto.getStartTime().withZoneSameInstant(ZoneId.of(departureAddress.getCity().getTimeZone()));
        ZonedDateTime endTime = updateTripDto.getEndTime().withZoneSameInstant(ZoneId.of(destinationAddress.getCity().getTimeZone()));

        trip.setDepartureAddress(departureAddress);
        trip.setDestinationAddress(destinationAddress);
        trip.setStartTime(setTimeZone(startTime, "UTC"));
        trip.setEndTime(setTimeZone(endTime, "UTC"));
        trip.setDuration(calculateDuration(trip));
        trip.setAvailablePassengersCount(updateTripDto.getAvailablePassengersCount());
        trip.setPassengersCapacity(updateTripDto.getPassengersCapacity());
        trip.setCaption(updateTripDto.getCaption());
        trip.setPrice(updateTripDto.getPrice());
        trip.setTransportType(updateTripDto.getTransportType());
        trip.setModifiedAt(ZonedDateTime.now());

        Trip updatedTrip = tripRepository.save(trip);

        return tripMapper.toReadTripDto(updatedTrip);
    }

    @Override
    public Page<ReadTripDto> generateTrip(GenerateTripDto generateTripDto) {
        int days = generateTripDto.getDays();
        int hours = generateTripDto.getHours();
        int minutes = generateTripDto.getMinutes();

        List<ReadTripDto> generatedTrips = new ArrayList<>();
        LocalDate tempDate = generateTripDto.getGenerationStartDate();
        while (!tempDate.toString().equals(generateTripDto.getGenerationEndDate().plusDays(1).toString())) {
            if (generateTripDto.getDaysOfWeek().contains(tempDate.getDayOfWeek().toString())) {
                Address departureAddress = addressService.getById(generateTripDto.getDepartureAddressId());
                Address destinationAddress = addressService.getById(generateTripDto.getDestinationAddressId());
                ZonedDateTime startTime = ZonedDateTime.of(tempDate, generateTripDto.getStartTime(), ZoneId.of(departureAddress.getCity().getTimeZone()));
                ZonedDateTime endTime = ZonedDateTime.of(tempDate.plusDays(days), generateTripDto.getStartTime().plusHours(hours).plusMinutes(minutes), ZoneId.of(destinationAddress.getCity().getTimeZone()));

                CreateTripDto trip = new CreateTripDto();
                trip.setUserId(generateTripDto.getUserId());
                trip.setDepartureAddressId(generateTripDto.getDepartureAddressId());
                trip.setDestinationAddressId(generateTripDto.getDestinationAddressId());
                trip.setStartTime(startTime);
                trip.setEndTime(endTime);
                trip.setAvailablePassengersCount(generateTripDto.getAvailablePassengersCount());
                trip.setPassengersCapacity(generateTripDto.getPassengersCapacity());
                trip.setPrice(generateTripDto.getPrice());
                trip.setCaption(generateTripDto.getCaption());
                trip.setTransportType(generateTripDto.getTransportType());
                generatedTrips.add(create(trip));
            }
            tempDate = tempDate.plusDays(1);
        }
        return new PageImpl<>(generatedTrips);
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

    private Trip getEntityById(String id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        return optionalTrip.orElseThrow(() -> new EntityNotFoundException("There is no Trip with id " + id));
    }

}
