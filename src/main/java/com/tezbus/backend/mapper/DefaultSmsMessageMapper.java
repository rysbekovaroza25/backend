package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.entity.SmsMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultSmsMessageMapper implements SmsMessageMapper {

	@Override
	@Transactional(readOnly = true)
	public ReadSmsMessageDto toReadSmsMessageDto(SmsMessage smsMessage) {
		ReadSmsMessageDto smsMessageDto = new ReadSmsMessageDto();
		smsMessageDto.setId(smsMessage.getId());
		smsMessageDto.setPhoneNumber(smsMessage.getPhoneNumber());
		smsMessageDto.setTripId(smsMessage.getTripId());
		smsMessageDto.setContent(smsMessage.getContent());
		smsMessageDto.setCreatedAt(smsMessage.getCreatedAt());
		smsMessageDto.setModifiedAt(smsMessage.getModifiedAt());

		return smsMessageDto;
	}
}
