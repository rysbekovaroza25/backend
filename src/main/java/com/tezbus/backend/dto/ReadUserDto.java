package com.tezbus.backend.dto;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadUserDto {
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private TransportType transportType;
    private String transportModel;
    private String transportNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
