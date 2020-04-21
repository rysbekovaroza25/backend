package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.Driver;

public interface SmsMessageSenderService {
    ReadSmsMessageDto sendOnDriverRegistration(Driver driver);
}
