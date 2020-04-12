package com.tezbus.backend.pageable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TripSearchRequest {
    private UUID departureCity;
    private UUID destinationCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer seatsCount = 0;
}