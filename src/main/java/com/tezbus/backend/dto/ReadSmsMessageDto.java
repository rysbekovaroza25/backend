package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadSmsMessageDto {
    private String id;
    private String phoneNumber;
    private String content;
    private String tripId;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
