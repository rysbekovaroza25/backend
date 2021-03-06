package com.tezbus.backend.service;

import com.tezbus.backend.dto.*;
import com.tezbus.backend.pageable.TripSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {
    Page<ReadTripDto> findTrips(TripSearchRequest tripSearchRequest, Pageable pageable);

    Page<ReadTripDto> getAllByUserId(String userId, Pageable pageable);

    ReadTripDto getById(String id);

    ReadTripDto create(CreateTripDto createTripDto);

    ReadTripDto update(String id, UpdateTripDto updateTripDto);

    ReadTripDto delete(String id);

    Page<ReadTripDto> generateTrip(GenerateTripDto generateTripDto);

    void sendPassengerDetails(String id, SendPassengerDetailsDto sendPassengerDetailsDto);
}
