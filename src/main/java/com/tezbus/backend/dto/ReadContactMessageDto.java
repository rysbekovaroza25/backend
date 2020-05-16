package com.tezbus.backend.dto;

import com.tezbus.backend.enums.ContactMessageStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadContactMessageDto {
    private String id;
    private String name;
    private String email;
    private String description;
    private ContactMessageStatus contactMessageStatus;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
