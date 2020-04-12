package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateContactMessageDto;
import com.tezbus.backend.dto.ReadContactMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ContactMessageService {
    Page<ReadContactMessageDto> getAll(Pageable pageable);

    ReadContactMessageDto create(CreateContactMessageDto createContactMessageDto);

    ReadContactMessageDto setAsReviewed(UUID id);
}
