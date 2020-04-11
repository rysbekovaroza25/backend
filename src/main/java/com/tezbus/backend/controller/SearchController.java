package com.tezbus.backend.controller;

import com.tezbus.backend.dto.ReadTripDto;
import com.tezbus.backend.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public List<ReadTripDto> getSchedules(
            @RequestParam(value = "departureCity") String departureCity,
            @RequestParam(value = "destinationCity") String destinationCity,
            @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate,
            @RequestParam(value = "seatsCount", defaultValue = "0") Integer seatsCount
    ) {
        return tripService.findTrips(departureCity, destinationCity, localDate, seatsCount);
    }
}
