package com.tezbus.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tezbus.backend.dto.CreateSubmissionDto;
import com.tezbus.backend.dto.ReadSubmissionDto;
import com.tezbus.backend.pageable.SubmissionPageRequest;
import com.tezbus.backend.pageable.SubmissionSearchRequest;
import com.tezbus.backend.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping
    public ReadSubmissionDto create(@RequestParam("front") MultipartFile frontFile,
                                    @RequestParam("back") MultipartFile backFile,
                                    @RequestParam("body") String createSubmissionDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CreateSubmissionDto createSubmissionDtoObject = new CreateSubmissionDto();
        try {
            createSubmissionDtoObject = mapper.readValue(createSubmissionDto, CreateSubmissionDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return submissionService.create(frontFile, backFile, createSubmissionDtoObject);
    }

    @GetMapping
    public Page<ReadSubmissionDto> getAll(SubmissionSearchRequest submissionSearchRequest, SubmissionPageRequest submissionPageRequest) {
        return new PageImpl(submissionService.getAll(submissionSearchRequest, submissionPageRequest));
    }

    @PutMapping("/{id}/approve")
    public ReadSubmissionDto approve(@PathVariable UUID id) {
        return submissionService.approve(id);
    }

    @PutMapping("/{id}/decline")
    public ReadSubmissionDto decline(@PathVariable UUID id) {
        return submissionService.decline(id);
    }


}
