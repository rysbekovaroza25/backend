package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadContactMessageDto;
import com.tezbus.backend.entity.ContactMessage;

public interface ContactMessageMapper {
    ReadContactMessageDto toReadContactMessageDto(ContactMessage contactMessage);
}
