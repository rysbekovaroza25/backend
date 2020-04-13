package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadItemDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    private ReadCityDto fromCity;
    private ReadCityDto toCity;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private ReadDriverDto assignedDriverDto;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
