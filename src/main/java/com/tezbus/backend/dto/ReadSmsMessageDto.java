package com.tezbus.backend.dto;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadSmsMessageDto {
	private UUID id;
	private String phoneNumber;
	private String content;
	private String tripId;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;
}
