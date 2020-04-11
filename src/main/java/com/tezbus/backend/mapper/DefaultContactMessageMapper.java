package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadContactMessageDto;
import com.tezbus.backend.entity.ContactMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultContactMessageMapper implements ContactMessageMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadContactMessageDto toReadContactMessageDto(ContactMessage contactMessage) {
        ReadContactMessageDto contactMessageDto = new ReadContactMessageDto();
        contactMessageDto.setId(contactMessage.getId());
        contactMessageDto.setName(contactMessage.getName());
        contactMessageDto.setEmail(contactMessage.getEmail());
        contactMessageDto.setDescription(contactMessage.getDescription());
        contactMessageDto.setContactMessageStatus(contactMessage.getContactMessageStatus());
        contactMessageDto.setCreatedAt(contactMessage.getCreatedAt());
        contactMessageDto.setModifiedAt(contactMessage.getModifiedAt());

        return contactMessageDto;
    }
}
