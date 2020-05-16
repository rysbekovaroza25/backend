package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionDto;
import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.pageable.SubmissionSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SubmissionService {
    ReadSubmissionDto create(
            MultipartFile frontFile,
            MultipartFile backFile,
            CreateSubmissionDto createSubmissionDto
    ) throws IOException;

    Page<ReadSubmissionDto> getAll(SubmissionSearchRequest submissionSearchRequest, Pageable pageable);

    ReadSubmissionDto approve(String id);

    ReadSubmissionDto decline(String id);
}
