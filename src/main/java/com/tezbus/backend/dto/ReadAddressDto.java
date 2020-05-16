package com.tezbus.backend.dto;

import lombok.Data;

@Data
public class ReadAddressDto {
    private String id;
    private String streetName;
    private ReadCityDto city;
}
