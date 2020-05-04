package com.tezbus.backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ReadCityDto {
	private UUID id;
	private String name;
	private String country;
	private String timeZone;
	private BigDecimal geoLatitude;
	private BigDecimal geoLongitude;
}
