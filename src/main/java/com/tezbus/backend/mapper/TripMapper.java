package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.entity.Trip;

public interface TripMapper {
    ReadTripDto toReadTripDto(Trip trip);
}
