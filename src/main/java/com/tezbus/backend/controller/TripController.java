package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateTripDto;
import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.dto.UpdateTripDto;
import com.tezbus.backend.pageable.TripPageRequest;
import com.tezbus.backend.pageable.TripSearchRequest;
import com.tezbus.backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public Page<ReadTripDto> getSchedules(TripSearchRequest tripSearchRequest, TripPageRequest tripPageRequest) {
        return tripService.findTrips(tripSearchRequest, tripPageRequest);
    }

    @GetMapping("/getByDriverId/{driverId}")
    public Page<ReadTripDto> getAllByDriverId(@PathVariable UUID driverId, TripPageRequest tripPageRequest) {
        return tripService.getAllByDriverId(driverId, tripPageRequest);
    }

    @GetMapping("/{id}")
    public ReadTripDto getById(@PathVariable UUID id) {
        return tripService.getById(id);
    }

    @PostMapping
    public ReadTripDto create(@RequestBody CreateTripDto createTripDto) {
        return tripService.create(createTripDto);
    }

    @PutMapping("/{id}")
    public ReadTripDto update(@PathVariable UUID id, @RequestBody UpdateTripDto updateTripDto) {
        return tripService.update(id, updateTripDto);
    }

    @DeleteMapping("/{id}")
    public ReadTripDto delete(@PathVariable UUID id) {
        return tripService.delete(id);
    }
}
