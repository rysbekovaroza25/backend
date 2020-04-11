package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateEmailDto;
import com.tezbus.backend.dto.ReadEmailDto;
import com.tezbus.backend.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification/emails")
public class EmailController {

	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping("/send")
	public ReadEmailDto send(@RequestBody @Validated CreateEmailDto createEmailDto) {
		return emailSenderService.send(createEmailDto);
	}
}
