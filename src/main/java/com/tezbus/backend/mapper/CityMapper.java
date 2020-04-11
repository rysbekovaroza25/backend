package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.entity.City;

public interface CityMapper {
    ReadCityDto toReadCityDto(City city);
}
