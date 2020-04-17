package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCityDto {

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String timeZone;
}
