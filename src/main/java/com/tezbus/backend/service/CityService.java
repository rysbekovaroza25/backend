package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateCityDto;
import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.dto.UpdateCityDto;
import com.tezbus.backend.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    ReadCityDto create(CreateCityDto createCityDto);

    ReadCityDto update(String id, UpdateCityDto updateCityDto);

    City getById(String id);

    ReadCityDto getCityById(String id);

    City getByName(String name);

    Page<ReadCityDto> getAll(Pageable pageable);

    void delete(String id);

}
