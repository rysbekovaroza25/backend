package com.tezbus.backend.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateSubmissionCommentDto {

    @NotNull
    private String comment;
}
