package com.tezbus.backend.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateSmsMessageDto {
	@NotNull
	private String phoneNumber;

	@NotNull
	private String content;

	private String tripId;
}
