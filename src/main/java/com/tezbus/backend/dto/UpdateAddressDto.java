package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateAddressDto {

    @NotNull
    private String cityId;

    @NotNull
    private String streetName;
}
