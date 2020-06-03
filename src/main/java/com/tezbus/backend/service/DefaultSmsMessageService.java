package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSmsMessageDto;
import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.SmsMessage;
import com.tezbus.backend.enums.NotificationType;
import com.tezbus.backend.mapper.SmsMessageMapper;
import com.tezbus.backend.repository.SmsMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class DefaultSmsMessageService implements SmsMessageService {

    @Autowired
    private SmsMessageRepository smsMessageRepository;

    @Autowired
    private SmsMessageMapper smsMessageMapper;

    @Override
    @Transactional
    public ReadSmsMessageDto create(CreateSmsMessageDto createSmsMessageDto) {
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setPhoneNumber(createSmsMessageDto.getPhoneNumber());
        smsMessage.setContent(createSmsMessageDto.getContent());
        smsMessage.setTripId(createSmsMessageDto.getTripId());
        smsMessage.setNotificationType(createSmsMessageDto.getNotificationType());
        smsMessage.setCreatedAt(ZonedDateTime.now());
        smsMessage.setModifiedAt(ZonedDateTime.now());

        SmsMessage savedMessage = smsMessageRepository.save(smsMessage);
        return smsMessageMapper.toReadSmsMessageDto(savedMessage);
    }
}
