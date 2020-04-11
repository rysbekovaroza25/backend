package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateSmsMessageDto;
import com.tezbus.backend.dto.ReadSmsMessageDto;

public interface SmsMessageService {
	ReadSmsMessageDto create(CreateSmsMessageDto createSmsMessageDto);
}
