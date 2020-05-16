package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadEmailDto {
    private String id;
    private String title;
    private String body;
    private String tripId;
    private String recipientEmail;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
