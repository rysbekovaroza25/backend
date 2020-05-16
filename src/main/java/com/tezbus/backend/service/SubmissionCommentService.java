package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionCommentDto;
import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.dto.UpdateSubmissionCommentDto;

public interface SubmissionCommentService {
    ReadSubmissionCommentDto create(String id, CreateSubmissionCommentDto createSubmissionCommentDto);

    ReadSubmissionCommentDto update(String id, UpdateSubmissionCommentDto updateSubmissionCommentDto);
}
