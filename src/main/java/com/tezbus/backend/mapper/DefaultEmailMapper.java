package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadEmailDto;
import com.tezbus.backend.entity.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultEmailMapper implements EmailMapper {

	@Override
	@Transactional(readOnly = true)
	public ReadEmailDto toReadEmailDto(Email email) {
		ReadEmailDto emailDto = new ReadEmailDto();
		emailDto.setId(email.getId());
		emailDto.setTitle(email.getTitle());
		emailDto.setBody(email.getBody());
		emailDto.setRecipientEmail(email.getRecipientEmail());
		emailDto.setCreatedAt(email.getCreatedAt());
		emailDto.setModifiedAt(email.getModifiedAt());

		return emailDto;
	}
}
