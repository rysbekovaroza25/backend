package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.entity.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultCityMapper implements CityMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadCityDto toReadCityDto(City city) {
        ReadCityDto readCityDto = new ReadCityDto();
        readCityDto.setId(city.getId());
        readCityDto.setName(city.getName());
        readCityDto.setCountry(city.getCountry());
        readCityDto.setTimeZone(city.getTimeZone());
        readCityDto.setGeoLatitude(city.getGeoLatitude());
        readCityDto.setGeoLongitude(city.getGeoLongitude());
        return readCityDto;
    }
}
