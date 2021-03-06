package com.tezbus.backend.dto;

import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreateTripDto {

    @NotNull
    private String userId;

    @NotNull
    private String departureAddressId;

    @NotNull
    private String destinationAddressId;

    @NotNull
    private ZonedDateTime endTime;

    @NotNull
    private ZonedDateTime startTime;

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
