package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.SmsMessage;

public interface SmsMessageMapper {
	ReadSmsMessageDto toReadSmsMessageDto(SmsMessage smsMessage);
}
