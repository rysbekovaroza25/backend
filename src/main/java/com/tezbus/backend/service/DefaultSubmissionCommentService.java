package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSubmissionCommentDto;
import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.dto.UpdateSubmissionCommentDto;
import com.tezbus.backend.entity.Submission;
import com.tezbus.backend.entity.SubmissionComment;
import com.tezbus.backend.mapper.SubmissionCommentMapper;
import com.tezbus.backend.repository.SubmissionCommentRepository;
import com.tezbus.backend.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultSubmissionCommentService implements SubmissionCommentService {

    @Autowired
    private SubmissionCommentRepository submissionCommentRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionCommentMapper submissionCommentMapper;

    @Override
    @Transactional
    public ReadSubmissionCommentDto create(UUID submissionId, CreateSubmissionCommentDto createSubmissionCommentDto) {
        Optional<Submission> optionalSubmission = submissionRepository.findById(submissionId);
        Submission submission = optionalSubmission.orElseThrow(() -> new EntityNotFoundException("There is no Submission with id: " + submissionId));

        SubmissionComment submissionComment = new SubmissionComment();
        submissionComment.setComment(createSubmissionCommentDto.getComment());
        submissionComment.setSubmission(submission);
        submissionComment.setCreatedAt(ZonedDateTime.now());
        submissionComment.setModifiedAt(ZonedDateTime.now());

        SubmissionComment savedSubmissionComment = submissionCommentRepository.save(submissionComment);

        return submissionCommentMapper.toSubmissionCommentDto(savedSubmissionComment);
    }

    @Override
    @Transactional
    public ReadSubmissionCommentDto update(UUID id, UpdateSubmissionCommentDto updateSubmissionCommentDto) {
        Optional<SubmissionComment> optionalSubmissionComment = submissionCommentRepository.findById(id);
        SubmissionComment submissionComment = optionalSubmissionComment.orElseThrow(() -> new EntityNotFoundException("There is no Submission Comment with id: " + id));

        submissionComment.setComment(updateSubmissionCommentDto.getComment());
        submissionComment.setModifiedAt(ZonedDateTime.now());

        SubmissionComment savedSubmissionComment = submissionCommentRepository.save(submissionComment);

        return submissionCommentMapper.toSubmissionCommentDto(savedSubmissionComment);
    }
}
