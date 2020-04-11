package com.tezbus.backend.dto;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadEmailDto {
	private UUID uuid;
	private String title;
	private String body;
	private String tripId;
	private String recipientEmail;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;
}
