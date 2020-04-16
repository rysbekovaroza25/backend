package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateCityDto {
    @NotNull
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String timeZone;

    @NotNull
    private boolean isDeleted;
}
