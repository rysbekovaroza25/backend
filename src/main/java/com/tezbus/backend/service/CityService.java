package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateCityDto;
import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.dto.UpdateCityDto;
import com.tezbus.backend.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CityService {
    ReadCityDto create(CreateCityDto createCityDto);

    ReadCityDto update(UUID id, UpdateCityDto updateCityDto);

    City getById(UUID id);

    ReadCityDto getCityById(UUID id);

    City getByName(String name);

    Page<ReadCityDto> getAll(Pageable pageable);

    void delete(UUID id);

}
