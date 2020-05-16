package com.tezbus.backend.repository;

import com.tezbus.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, String> {
    Optional<City> findByNameIgnoreCase(String name);

    City findByName(String name);

    City findByNameAndCountry(String name, String country);
}
