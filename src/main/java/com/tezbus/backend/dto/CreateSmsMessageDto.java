package com.tezbus.backend.dto;

import com.tezbus.backend.enums.NotificationType;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CreateSmsMessageDto {
	@NotNull
	private String phoneNumber;

	@NotNull
	private String content;

	private String tripId;

	@NotNull
	private NotificationType notificationType;
}
