package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateContactMessageDto;
import com.tezbus.backend.dto.ReadContactMessageDto;
import com.tezbus.backend.entity.ContactMessage;
import com.tezbus.backend.enums.ContactMessageStatus;
import com.tezbus.backend.mapper.ContactMessageMapper;
import com.tezbus.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultContactMessageService implements ContactMessageService {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Autowired
    private ContactMessageMapper contactMessageMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ReadContactMessageDto> getAll(Pageable pageable) {
        Page<ContactMessage> contactMessages = contactMessageRepository.findAll(pageable);

        return new PageImpl<>(
                contactMessages.stream()
                        .map(it -> contactMessageMapper.toReadContactMessageDto(it))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public ReadContactMessageDto create(CreateContactMessageDto createContactMessageDto) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(createContactMessageDto.getName());
        contactMessage.setDescription(createContactMessageDto.getDescription());
        contactMessage.setEmail(createContactMessageDto.getEmail());
        contactMessage.setContactMessageStatus(ContactMessageStatus.PENDING);
        contactMessage.setCreatedAt(ZonedDateTime.now());
        contactMessage.setModifiedAt(ZonedDateTime.now());

        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessage);

        return contactMessageMapper.toReadContactMessageDto(savedContactMessage);
    }

    @Override
    @Transactional
    public ReadContactMessageDto setAsReviewed(UUID id) {
        Optional<ContactMessage> optionalContactMessage = contactMessageRepository.findById(id);
        ContactMessage contactMessage = optionalContactMessage.orElseThrow(
                () -> new EntityNotFoundException("There is no Contact Message with id: " + id));

        contactMessage.setContactMessageStatus(ContactMessageStatus.REVIEWED);
        contactMessage.setModifiedAt(ZonedDateTime.now());

        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessage);

        return contactMessageMapper.toReadContactMessageDto(savedContactMessage);
    }
}
