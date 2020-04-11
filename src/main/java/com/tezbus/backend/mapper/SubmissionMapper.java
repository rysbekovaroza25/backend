package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.entity.Submission;

public interface SubmissionMapper {
    ReadSubmissionDto toReadSubmissionDto(Submission submission);
}
