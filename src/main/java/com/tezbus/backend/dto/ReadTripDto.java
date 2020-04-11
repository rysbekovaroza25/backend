package com.tezbus.backend.dto;

import com.tezbus.backend.enums.TransportType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class ReadTripDto {
	private UUID id;
	private ReadDriverDto driver;
	private ReadAddressDto departureAddress;
	private ReadAddressDto destinationAddress;
	private ZonedDateTime startTime;
	private ZonedDateTime endTime;
	private long duration;
	private int availablePassengersCount;
	private int passengersCapacity;
	private BigDecimal price;
	private String caption;
	private TransportType transportType;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;
}
