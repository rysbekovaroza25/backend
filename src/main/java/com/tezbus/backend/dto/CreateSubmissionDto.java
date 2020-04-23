package com.tezbus.backend.dto;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
public class CreateSubmissionDto {
    private Date birthDate;
    private String email;
    private Gender gender;
    private UUID driverId;
    private String transportModel;
    private String transportNumber;
    private TransportType transportType;
}