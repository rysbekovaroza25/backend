package com.tezbus.backend.entity;

import com.tezbus.backend.enums.NotificationType;
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
	private String id = UUID.randomUUID().toString();

	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;

	@Column(name = "content", columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(name = "trip_id")
	private String tripId;

	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type")
	private NotificationType notificationType;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "modified_at", nullable = false)
	private ZonedDateTime modifiedAt;
}
