package com.tezbus.backend.service;

import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address getById(UUID id);
    List<Address> getAllByCity(City city);
}
