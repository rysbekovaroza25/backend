package com.tezbus.backend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReadCityDto {
    private String id;
    private String name;
    private String country;
    private String timeZone;
    private BigDecimal geoLatitude;
    private BigDecimal geoLongitude;
}
