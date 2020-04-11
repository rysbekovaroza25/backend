package com.tezbus.backend.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sms_messages")
public class SmsMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = UUID.randomUUID();

	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(name = "trip_id")
	private String tripId;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "modified_at", nullable = false)
	private ZonedDateTime modifiedAt;
}
