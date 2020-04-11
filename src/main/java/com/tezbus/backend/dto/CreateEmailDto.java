package com.tezbus.backend.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateEmailDto {
	@NotNull
	private String recipientEmail;

	@NotNull
	private String title;

	@NotNull
	private String body;

	private String tripId;
}
