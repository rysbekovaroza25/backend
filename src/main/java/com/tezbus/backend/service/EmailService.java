package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateEmailDto;
import com.tezbus.backend.dto.ReadEmailDto;

public interface EmailService {
	ReadEmailDto create(CreateEmailDto createEmailDto);
}
