package com.tezbus.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "addresses",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"city_id", "street_name"})
		}
)
public class Address {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private UUID id = UUID.randomUUID();

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@Column(name = "street_name", nullable = false)
	private String streetName;

	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt;

	@Column(name = "modified_at")
	private ZonedDateTime modifiedAt;

}
