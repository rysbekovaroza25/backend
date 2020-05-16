package com.tezbus.backend.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ReadSubmissionCommentDto {
    private String id;
    private String comment;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
}
