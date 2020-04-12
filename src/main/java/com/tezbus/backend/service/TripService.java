package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateTripDto;
import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.dto.UpdateTripDto;
import com.tezbus.backend.pageable.TripSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TripService {
    Page<ReadTripDto> findTrips(TripSearchRequest tripSearchRequest, Pageable pageable);

    Page<ReadTripDto> getAllByDriverId(UUID tripDtoId, Pageable pageable);

    ReadTripDto getById(UUID id);

    ReadTripDto create(CreateTripDto createTripDto);

    ReadTripDto update(UUID id, UpdateTripDto updateTripDto);

    ReadTripDto delete(UUID id);
}
