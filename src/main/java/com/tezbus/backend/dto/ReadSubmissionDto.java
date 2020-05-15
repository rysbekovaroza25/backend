package com.tezbus.backend.dto;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.SubmissionStatus;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ReadSubmissionDto {
    private UUID id;
    private Date birthDate;
    private String email;
    private Gender gender;
    private ReadUserDto readUserDto;
    private String driverLicenseCardFrontUrl;
    private String driverLicenseCardBackUrl;
    private String transportModel;
    private String transportNumber;
    private TransportType transportType;
    private SubmissionStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
    private List<ReadSubmissionCommentDto> readSubmissionCommentDtos;
}
