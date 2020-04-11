package com.tezbus.backend.repository;

import com.tezbus.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
	Optional<City> findByNameIgnoreCase(String name);
	City findByName(String name);
	City findByNameAndCountry(String name, String country);
}
