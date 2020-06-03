package com.tezbus.backend.dto;

import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class GenerateTripDto {

    @NotNull
    private String userId;

    @NotNull
    private String departureAddressId;

    @NotNull
    private String destinationAddressId;

    @NotNull
    private LocalDate generationStartDate;

    @NotNull
    private LocalDate generationEndDate;

    @NotNull
    private LocalTime startTime;

    // duration
    @NotNull
    private int days, hours, minutes;

    @NotNull
    private List daysOfWeek;

    @NotNull
    private int availablePassengersCount;

    @NotNull
    private int passengersCapacity;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String caption;

    @NotNull
    private TransportType transportType;
}
