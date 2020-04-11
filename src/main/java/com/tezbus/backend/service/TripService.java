package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadTripDto;

import java.time.LocalDate;
import java.util.List;

public interface TripService {
    List<ReadTripDto> findTrips(
            String departureCity,
            String destinationCity,
            LocalDate localDate,
            int seatsCount
    );
}
