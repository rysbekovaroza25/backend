package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadEmailDto;
import com.tezbus.backend.entity.Email;

public interface EmailMapper {
	ReadEmailDto toReadEmailDto(Email email);
}
