package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionDto;
import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.pageable.SubmissionSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface SubmissionService {
    ReadSubmissionDto create(
            MultipartFile frontFile,
            MultipartFile backFile,
            CreateSubmissionDto createSubmissionDto
    ) throws IOException;

    Page<ReadSubmissionDto> getAll(SubmissionSearchRequest submissionSearchRequest, Pageable pageable);

    ReadSubmissionDto approve(UUID id);

    ReadSubmissionDto decline(UUID id);
}
