package com.tezbus.backend.service;

import com.tezbus.backend.entity.City;

import java.util.UUID;

public interface CityService {
    City getById(UUID id);
    City getByName(String name);
}
