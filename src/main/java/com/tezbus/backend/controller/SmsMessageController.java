package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateSmsMessageDto;
import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.service.SmsMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification/sms_messages")
public class SmsMessageController {

	@Autowired
	private SmsMessageSenderService smsMessageSenderService;

	@PostMapping("/send")
	public ReadSmsMessageDto send(@RequestBody @Validated CreateSmsMessageDto createSmsMessageDto) {
		return smsMessageSenderService.send(createSmsMessageDto);
	}

}
