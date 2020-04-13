package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DefaultTripMapper implements TripMapper {

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    @Transactional(readOnly = true)
    public ReadTripDto toReadTripDto(Trip trip) {
        ReadTripDto readTripDto = new ReadTripDto();
        readTripDto.setId(trip.getId());
        readTripDto.setDriver(driverMapper.toReadDriverDto(trip.getDriver()));
        readTripDto.setDepartureAddress(addressMapper.toReadAddressDto(trip.getDepartureAddress()));
        readTripDto.setDestinationAddress(addressMapper.toReadAddressDto(trip.getDestinationAddress()));
        readTripDto.setDuration(calculateDuration(trip));
        readTripDto.setStartTime(setTimeZone(trip.getStartTime(), trip.getDepartureAddress().getCity().getTimeZone()));
        readTripDto.setEndTime(setTimeZone(trip.getEndTime(), trip.getDestinationAddress().getCity().getTimeZone()));
        readTripDto.setAvailablePassengersCount(trip.getAvailablePassengersCount());
        readTripDto.setPassengersCapacity(trip.getPassengersCapacity());
        readTripDto.setPrice(trip.getPrice());
        readTripDto.setCaption(trip.getCaption());
        readTripDto.setTransportType(trip.getTransportType());
        readTripDto.setCreatedAt(trip.getCreatedAt());
        readTripDto.setModifiedAt(trip.getModifiedAt());
        readTripDto.setDeleted(trip.isDeleted());

        return readTripDto;
    }


    private ZonedDateTime setTimeZone(ZonedDateTime dateTime, String zoneId) {
        return dateTime.withZoneSameInstant(ZoneId.of(zoneId));
    }

    private long calculateDuration(Trip trip) {
        if (trip.getDuration() <= 0) {
            return trip.getDuration();
        }
        return ChronoUnit.MINUTES.between(trip.getStartTime(), trip.getEndTime());
    }
}
