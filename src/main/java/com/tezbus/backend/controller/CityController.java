package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateCityDto;
import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.dto.UpdateCityDto;
import com.tezbus.backend.mapper.CityMapper;
import com.tezbus.backend.pageable.CityPageRequest;
import com.tezbus.backend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ReadCityDto create(@RequestBody CreateCityDto createCityDto) {
        return cityService.create(createCityDto);
    }

    @PutMapping("/{cityId}")
    public ReadCityDto update(@PathVariable UUID cityId, @RequestBody UpdateCityDto updateCityDto) {
        return cityService.update(cityId, updateCityDto);
    }

    @GetMapping("/{cityId}")
    public ReadCityDto getById(@PathVariable UUID cityId) {
        return cityService.getCityById(cityId);
    }

    @GetMapping
    public Page<ReadCityDto> getAll(CityPageRequest cityPageRequest) {
        return cityService.getAllCities(cityPageRequest);
    }

    @DeleteMapping("/{cityId}")
    public void delete(@PathVariable UUID cityId) {
        cityService.delete(cityId);
    }
}