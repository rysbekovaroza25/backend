package com.tezbus.backend.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ReadAddressDto {
	private UUID id;
	private String streetName;
	private ReadCityDto city;
}
