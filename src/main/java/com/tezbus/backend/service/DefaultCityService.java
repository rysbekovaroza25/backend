package com.tezbus.backend.service;

import com.tezbus.backend.entity.City;
import com.tezbus.backend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCityService implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public City getById(UUID id) {
        Optional<City> optionalCity = cityRepository.findById(id);

        return optionalCity.orElseThrow(() -> new EntityNotFoundException("There is no City with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public City getByName(String name) {
        Optional<City> optionalCity = cityRepository.findByNameIgnoreCase(name);

        return optionalCity.orElseThrow(() -> new EntityNotFoundException("There is no City with name: " + name));
    }


}
