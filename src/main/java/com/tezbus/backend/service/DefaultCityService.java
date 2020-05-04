package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateCityDto;
import com.tezbus.backend.dto.ReadCityDto;
import com.tezbus.backend.dto.UpdateCityDto;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.mapper.CityMapper;
import com.tezbus.backend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultCityService implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;

    @Override
    @Transactional
    public ReadCityDto create(CreateCityDto createCityDto) {
        City city = new City();
        city.setName(createCityDto.getName());
        city.setCountry(createCityDto.getCountry());
        city.setTimeZone(createCityDto.getTimeZone());
        city.setCreatedAt(ZonedDateTime.now());
        city.setModifiedAt(ZonedDateTime.now());
        city.setGeoLatitude(createCityDto.getGeoLatitude());
        city.setGeoLongitude(createCityDto.getGeoLongitude());
        city.setDeleted(false);

        City savedCity = cityRepository.save(city);

        return cityMapper.toReadCityDto(savedCity);
    }

    @Override
    @Transactional
    public ReadCityDto update(UUID id, UpdateCityDto updateCityDto) {
        City city = getById(id);
        city.setName(updateCityDto.getName());
        city.setCountry(updateCityDto.getCountry());
        city.setTimeZone(updateCityDto.getTimeZone());
        city.setModifiedAt(ZonedDateTime.now());
        city.setGeoLatitude(updateCityDto.getGeoLatitude());
        city.setGeoLongitude(updateCityDto.getGeoLongitude());

        City updatedCity = cityRepository.save(city);

        return cityMapper.toReadCityDto(updatedCity);
    }

    @Override
    @Transactional(readOnly = true)
    public City getById(UUID id) {
        Optional<City> optionalCity = cityRepository.findById(id);

        return optionalCity.orElseThrow(() -> new EntityNotFoundException("There is no City with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ReadCityDto getCityById(UUID id) {
        return cityMapper.toReadCityDto(getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public City getByName(String name) {
        Optional<City> optionalCity = cityRepository.findByNameIgnoreCase(name);

        return optionalCity.orElseThrow(() -> new EntityNotFoundException("There is no City with name: " + name));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadCityDto> getAll(Pageable pageable) {
        return new PageImpl<>(cityRepository.findAll(pageable)
                .stream()
                .filter(city -> !city.isDeleted())
                .map(city -> cityMapper.toReadCityDto(city))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        City city = getById(id);
        city.setModifiedAt(ZonedDateTime.now());
        city.setDeleted(true);

        cityRepository.save(city);
    }
}