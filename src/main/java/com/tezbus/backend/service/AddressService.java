package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateAddressDto;
import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.dto.UpdateAddressDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    ReadAddressDto create(CreateAddressDto createAddressDto);

    ReadAddressDto update(UUID id, UpdateAddressDto updateAddressDto);

    Address getById(UUID id);

    ReadAddressDto getAddressById(UUID id);

    List<Address> getAllByCity(City city);

    Page<ReadAddressDto> getAddressesByCity(UUID cityId);

    Page<ReadAddressDto> getAll(Pageable pageable);

    void delete(UUID id);
}
