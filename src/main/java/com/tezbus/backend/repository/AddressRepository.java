package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    boolean existsAddressByCityAndStreetName(City city, String streetName);

    Address findByCity_Name(String cityName);

    Address findByCity_NameAndStreetName(String cityName, String streetName);

    List<Address> findByCity(City city);
}
