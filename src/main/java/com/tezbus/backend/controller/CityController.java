package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateCityDto;
import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.dto.UpdateCityDto;
import com.tezbus.backend.pageable.CityPageRequest;
import com.tezbus.backend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ReadCityDto create(@RequestBody @Validated CreateCityDto createCityDto) {
        return cityService.create(createCityDto);
    }

    @PutMapping("/{id}")
    public ReadCityDto update(@PathVariable UUID id, @RequestBody @Validated UpdateCityDto updateCityDto) {
        return cityService.update(id, updateCityDto);
    }

    @GetMapping("/{id}")
    public ReadCityDto getById(@PathVariable UUID id) {
        return cityService.getCityById(id);
    }

    @GetMapping
    public Page<ReadCityDto> getAll(CityPageRequest cityPageRequest) {
        return cityService.getAll(cityPageRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        cityService.delete(id);
    }
}