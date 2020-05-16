package com.tezbus.backend.pageable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TripSearchRequest {
    private String departureCity;
    private String destinationCity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer seatsCount = 0;
}