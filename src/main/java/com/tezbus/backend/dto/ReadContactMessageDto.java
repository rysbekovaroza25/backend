package com.tezbus.backend.dto;

import com.tezbus.backend.enums.ContactMessageStatus;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadContactMessageDto {
    private UUID id;
    private String name;
    private String email;
    private String description;
    private ContactMessageStatus contactMessageStatus;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
