package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateSubmissionCommentDto {
    @NotNull
    private String comment;
}
