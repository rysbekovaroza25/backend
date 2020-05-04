package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UpdateCityDto {

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String timeZone;

    @NotNull
    private BigDecimal geoLatitude;

    @NotNull
    private BigDecimal geoLongitude;
}
