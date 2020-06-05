package com.tezbus.backend.controller;

import com.tezbus.backend.dto.*;
import com.tezbus.backend.pageable.TripPageRequest;
import com.tezbus.backend.pageable.TripSearchRequest;
import com.tezbus.backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public Page<ReadTripDto> getSchedules(TripSearchRequest tripSearchRequest, TripPageRequest tripPageRequest) {
        return tripService.findTrips(tripSearchRequest, tripPageRequest);
    }

    @GetMapping("/getByUserId/{userId}")
    public Page<ReadTripDto> getAllByUserId(@PathVariable String userId, TripPageRequest tripPageRequest) {
        return tripService.getAllByUserId(userId, tripPageRequest);
    }

    @GetMapping("/{id}")
    public ReadTripDto getById(@PathVariable String id) {
        return tripService.getById(id);
    }

    @PostMapping
    public ReadTripDto create(@RequestBody @Validated CreateTripDto createTripDto) {
        return tripService.create(createTripDto);
    }

    @PutMapping("/{id}")
    public ReadTripDto update(@PathVariable String id, @RequestBody @Validated UpdateTripDto updateTripDto) {
        return tripService.update(id, updateTripDto);
    }

    @PutMapping("/{id}/sendPassengerDetails")
    public void sendPassengerDetails(@PathVariable String id, @RequestBody @Validated SendPassengerDetailsDto sendPassengerDetailsDto) {
        tripService.sendPassengerDetails(id, sendPassengerDetailsDto);
    }

    @DeleteMapping("/{id}")
    public ReadTripDto delete(@PathVariable String id) {
        return tripService.delete(id);
    }

    @PostMapping("/{id}")
    public Page<ReadTripDto> generate(@PathVariable String id, @RequestBody @Validated GenerateTripDto generateTripDto){
        return tripService.generateTrip(generateTripDto);
    }
}
