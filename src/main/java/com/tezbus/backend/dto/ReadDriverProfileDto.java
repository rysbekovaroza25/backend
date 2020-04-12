package com.tezbus.backend.dto;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class ReadDriverProfileDto {
	private UUID id;
	private String userId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Gender gender;
	private String phoneNumber;
	private String email;
	private String transportModel;
	private String transportNumber;
	private TransportType transportType;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;
}
