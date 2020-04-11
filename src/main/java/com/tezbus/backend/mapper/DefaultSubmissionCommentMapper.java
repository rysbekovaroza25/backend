package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.entity.SubmissionComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultSubmissionCommentMapper implements SubmissionCommentMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadSubmissionCommentDto toSubmissionCommentDto(SubmissionComment submissionComment) {
        ReadSubmissionCommentDto submissionCommentDto = new ReadSubmissionCommentDto();
        submissionCommentDto.setId(submissionComment.getId());
        submissionCommentDto.setComment(submissionComment.getComment());
        submissionCommentDto.setCreatedAt(submissionComment.getCreatedAt());
        submissionCommentDto.setModifiedAt(submissionComment.getModifiedAt());

        return submissionCommentDto;
    }
}
