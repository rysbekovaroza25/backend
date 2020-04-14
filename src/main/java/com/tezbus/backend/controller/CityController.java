package com.tezbus.backend.controller;

import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ReadCityDto create() {

    }

    @PutMapping
    public ReadCityDto update() {

    }

    @GetMapping
    public ReadCityDto getById() {

    }

    @GetMapping
    public ReadCityDto getAll() {

    }

    @DeleteMapping
    public ReadCityDto delete() {

    }

}
