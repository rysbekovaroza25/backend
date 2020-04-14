package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateContactMessageDto;
import com.tezbus.backend.dto.ReadContactMessageDto;
import com.tezbus.backend.pageable.ContactMessagePageRequest;
import com.tezbus.backend.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contact_messages")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;

    @GetMapping
    public Page<ReadContactMessageDto> getAll(ContactMessagePageRequest contactMessagePageRequest) {
        return contactMessageService.getAll(contactMessagePageRequest);
    }

    @PostMapping
    public ReadContactMessageDto create(@RequestBody CreateContactMessageDto createContactMessageDto) {
        return contactMessageService.create(createContactMessageDto);
    }

    @PutMapping("/{id}")
    public ReadContactMessageDto setAsReviewed(@PathVariable UUID id) {
        return contactMessageService.setAsReviewed(id);
    }
}
