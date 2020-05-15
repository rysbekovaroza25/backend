package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.User;

public interface SmsMessageSenderService {
    ReadSmsMessageDto sendOnDriverRegistration(User driver);
}
