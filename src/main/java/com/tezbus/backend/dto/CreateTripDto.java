package com.tezbus.backend.dto;

import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateTripDto {

	@NotNull
	private UUID driverId;

	@NotNull
	private UUID departureAddressId;

	@NotNull
	private UUID destinationAddressId;

	@NotNull
	private String endTime;

	@NotNull
	private String startTime;

	@NotNull
	private int availablePassengersCount;

	@NotNull
	private int passengersCapacity;

	@NotNull
	private BigDecimal price;

	@NotNull
	private String caption;

	@NotNull
	private TransportType transportType;
}
