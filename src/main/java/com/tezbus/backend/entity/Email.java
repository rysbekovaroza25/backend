package com.tezbus.backend.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "emails")
public class Email {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private String id = UUID.randomUUID().toString();

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "body", columnDefinition = "TEXT", nullable = false)
	private String body;

	@Column(name = "recipient_email", nullable = false)
	private String recipientEmail;

	@Column(name = "trip_id")
	private String tripId;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "modified_at", nullable = false)
	private ZonedDateTime modifiedAt;
}
