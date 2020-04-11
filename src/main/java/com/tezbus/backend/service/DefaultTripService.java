package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.entity.Item;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.mapper.TripMapper;
import com.tezbus.backend.repository.AddressRepository;
import com.tezbus.backend.repository.CityRepository;
import com.tezbus.backend.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultTripService implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TripMapper tripMapper;

    @Override
    public List<ReadTripDto> findTrips(String departureCityName, String destinationCityName, LocalDate localDate, int seatsCount) {
        Optional<City> optionalDepartureCity = cityRepository.findByNameIgnoreCase(departureCityName);
        City departureCity = optionalDepartureCity.orElseThrow(() -> new EntityNotFoundException("There is no City with name: " + departureCityName));

        Optional<City> optionalDestinationCity = cityRepository.findByNameIgnoreCase(destinationCityName);
        City destinationCity = optionalDestinationCity.orElseThrow(() -> new EntityNotFoundException("There is no City with name: " + destinationCityName));

        List<Address> departuresAddresses = addressRepository.findByCity(departureCity);
        List<Address> destinationAddresses = addressRepository.findByCity(destinationCity);

        ZonedDateTime[] fromToDates = getFromToDates(localDate, departureCity.getTimeZone());

        List<Trip> trips = tripRepository.findByDepartureAddressInAndDestinationAddressInAndStartTimeGreaterThanEqualAndStartTimeLessThanEqual(
                departuresAddresses,
                destinationAddresses,
                fromToDates[0],
                fromToDates[1]
        );

        List<Trip> existingTripsWithSeats = trips.stream()
                .filter(it -> !it.isDeleted())
                .filter(it -> it.getAvailablePassengersCount() >= seatsCount)
                .collect(Collectors.toList());

        return existingTripsWithSeats.stream()
                .sorted(Comparator.comparing(Trip::getStartTime))
                .map(it -> tripMapper.toReadTripDto(it))
                .collect(Collectors.toList());
    }

    private ZonedDateTime[] getFromToDates(LocalDate localDate, String timeZone) {
        ZonedDateTime[] dates = new ZonedDateTime[2];
        ZonedDateTime cityTimeZone = ZonedDateTime.of(localDate, LocalTime.parse("00:00:00"), ZoneId.of(timeZone));

        dates[0] = cityTimeZone.withZoneSameInstant(ZoneId.of("UTC"));
        dates[1] = cityTimeZone.withZoneSameInstant(ZoneId.of("UTC")).plusDays(1);

        return dates;
    }

}
