package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateDriverDto {

    @NotNull
    private UUID userId;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
