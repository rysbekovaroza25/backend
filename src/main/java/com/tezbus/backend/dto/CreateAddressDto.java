package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateAddressDto {
    @NotNull
    private UUID cityId;

    @NotNull
    private String streetName;
}