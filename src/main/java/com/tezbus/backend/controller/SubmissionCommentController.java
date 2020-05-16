package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateSubmissionCommentDto;
import com.tezbus.backend.dto.ReadSubmissionCommentDto;
import com.tezbus.backend.dto.UpdateSubmissionCommentDto;
import com.tezbus.backend.service.SubmissionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/submissions/{submissionId}/comments")
public class SubmissionCommentController {

    @Autowired
    private SubmissionCommentService submissionCommentService;

    @PostMapping
    public ReadSubmissionCommentDto create(
            @PathVariable String submissionId,
            @RequestBody @Validated CreateSubmissionCommentDto createSubmissionCommentDto
    ) {
        return submissionCommentService.create(submissionId, createSubmissionCommentDto);
    }

    @PutMapping("/{id}")
    public ReadSubmissionCommentDto update(
            @PathVariable String id,
            @RequestBody @Validated UpdateSubmissionCommentDto updateSubmissionCommentDto
    ) {
        return submissionCommentService.update(id, updateSubmissionCommentDto);
    }
}