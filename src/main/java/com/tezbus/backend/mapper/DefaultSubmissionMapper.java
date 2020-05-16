package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultSubmissionMapper implements SubmissionMapper {

    @Autowired
    private SubmissionCommentMapper submissionCommentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReadSubmissionDto toReadSubmissionDto(Submission submission) {
        List<ReadSubmissionCommentDto> readSubmissionCommentDtos = submission.getSubmissionComments()
                .stream()
                .map(it -> submissionCommentMapper.toSubmissionCommentDto(it))
                .collect(Collectors.toList());

        ReadSubmissionDto submissionDto = new ReadSubmissionDto();
        submissionDto.setId(submission.getId());
        submissionDto.setReadUserProfileDto(userMapper.toReadUserProfileDto(submission.getUser()));
        submissionDto.setBirthDate(submission.getBirthDate());
        submissionDto.setEmail(submission.getEmail());
        submissionDto.setGender(submission.getGender());
        submissionDto.setDriverLicenseCardFrontUrl(submission.getDriverLicenseCardFrontUrl());
        submissionDto.setDriverLicenseCardBackUrl(submission.getDriverLicenseCardBackUrl());
        submissionDto.setTransportModel(submission.getTransportModel());
        submissionDto.setTransportNumber(submission.getTransportNumber());
        submissionDto.setTransportType(submission.getTransportType());
        submissionDto.setStatus(submission.getStatus());
        submissionDto.setCreatedAt(submission.getCreatedAt());
        submissionDto.setModifiedAt(submission.getModifiedAt());
        submissionDto.setReadSubmissionCommentDtos(readSubmissionCommentDtos);

        return submissionDto;
    }
}
