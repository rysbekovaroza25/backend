package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.entity.SubmissionComment;

public interface SubmissionCommentMapper {
    ReadSubmissionCommentDto toSubmissionCommentDto(SubmissionComment submissionComment);
}
