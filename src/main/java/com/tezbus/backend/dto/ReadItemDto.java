package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadItemDto {
    private String id;
    private String firstName;
    private String lastName;
    private String description;
    private ReadCityDto fromCity;
    private ReadCityDto toCity;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private ReadUserProfileDto assignedUserDto;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
