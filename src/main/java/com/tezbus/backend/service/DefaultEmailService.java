package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateEmailDto;
import com.tezbus.backend.dto.ReadEmailDto;
import com.tezbus.backend.entity.Email;
import com.tezbus.backend.mapper.EmailMapper;
import com.tezbus.backend.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;

@Service
public class DefaultEmailService implements EmailService {

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private EmailMapper emailMapper;

	@Override
	@Transactional
	public ReadEmailDto create(CreateEmailDto createEmailDto) {
		Email email = new Email();
		email.setTitle(createEmailDto.getTitle());
		email.setBody(createEmailDto.getBody());
		email.setTripId(createEmailDto.getTripId());
		email.setRecipientEmail(createEmailDto.getRecipientEmail());
		email.setCreatedAt(ZonedDateTime.now());
		email.setModifiedAt(ZonedDateTime.now());

		Email savedEmail = emailRepository.save(email);

		return emailMapper.toReadEmailDto(savedEmail);
	}
}
