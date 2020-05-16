package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateItemDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String description;

    @NotNull
    private String fromCity;

    @NotNull
    private String toCity;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;
}
