package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionDto;
import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.entity.Driver;
import com.tezbus.backend.entity.Submission;
import com.tezbus.backend.enums.SubmissionStatus;
import com.tezbus.backend.mapper.SubmissionMapper;
import com.tezbus.backend.pageable.SubmissionSearchRequest;
import com.tezbus.backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultSubmissionService implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private DriverService driverService;

    @Override
    @Transactional
    public ReadSubmissionDto create(MultipartFile frontFile, MultipartFile backFile, CreateSubmissionDto createSubmissionDto) throws IOException {
        Driver driver = driverService.getById(createSubmissionDto.getDriverId());

        Submission submission = new Submission();
        submission.setBirthDate(createSubmissionDto.getBirthDate());
        submission.setEmail(createSubmissionDto.getEmail());
        submission.setGender(createSubmissionDto.getGender());
        submission.setDriver(driver);
        submission.setTransportModel(createSubmissionDto.getTransportModel());
        submission.setTransportNumber(createSubmissionDto.getTransportNumber());
        submission.setTransportType(createSubmissionDto.getTransportType());
        submission.setStatus(SubmissionStatus.PENDING);
        submission.setCreatedAt(ZonedDateTime.now());

        String driverLicenseCardFrontUrl = fileUploadService.uploadFile(frontFile.getOriginalFilename(), frontFile);
        String driverLicenseCardBackUrl = fileUploadService.uploadFile(backFile.getOriginalFilename(), backFile);

        submission.setDriverLicenseCardFrontUrl(driverLicenseCardFrontUrl);
        submission.setDriverLicenseCardBackUrl(driverLicenseCardBackUrl);

        Submission savedSubmission = submissionRepository.save(submission);

        return submissionMapper.toReadSubmissionDto(savedSubmission);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadSubmissionDto> getAll(SubmissionSearchRequest submissionSearchRequest, Pageable pageable) {
        List<Submission> submissions = submissionRepository.findAllByStatus(submissionSearchRequest.getStatus(), pageable);

        return new PageImpl<>(submissions.stream().map(it -> submissionMapper.toReadSubmissionDto(it)).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ReadSubmissionDto approve(UUID id) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(id);
        Submission submission = optionalSubmission.orElseThrow(() -> new EntityNotFoundException("There is no Submission with id: " + id));

        submission.setStatus(SubmissionStatus.APPROVED);
        submission.setModifiedAt(ZonedDateTime.now());

        Submission updatedSubmission = submissionRepository.save(submission);

        return submissionMapper.toReadSubmissionDto(updatedSubmission);
    }

    @Override
    @Transactional
    public ReadSubmissionDto decline(UUID id) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(id);
        Submission submission = optionalSubmission.orElseThrow(() -> new EntityNotFoundException("There is no Submission with id: " + id));

        submission.setStatus(SubmissionStatus.DECLINED);
        submission.setModifiedAt(ZonedDateTime.now());

        Submission updatedSubmission = submissionRepository.save(submission);

        return submissionMapper.toReadSubmissionDto(updatedSubmission);
    }
}
