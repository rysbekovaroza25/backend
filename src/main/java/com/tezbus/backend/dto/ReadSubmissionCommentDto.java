package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadSubmissionCommentDto {
    private UUID id;
    private String comment;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
