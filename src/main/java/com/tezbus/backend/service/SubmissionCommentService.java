package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionCommentDto;
import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.dto.UpdateSubmissionCommentDto;
import java.util.UUID;

public interface SubmissionCommentService {
    ReadSubmissionCommentDto create(UUID submissionId, CreateSubmissionCommentDto createSubmissionCommentDto);

    ReadSubmissionCommentDto update(UUID id, UpdateSubmissionCommentDto updateSubmissionCommentDto);
}
