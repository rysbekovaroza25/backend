package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateItemDto {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String description;

    @NotNull
    private UUID fromCity;

    @NotNull
    private UUID toCity;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;
}
