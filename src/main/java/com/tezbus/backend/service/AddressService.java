package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateAddressDto;
import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.dto.UpdateAddressDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {
    ReadAddressDto create(CreateAddressDto createAddressDto);

    ReadAddressDto update(String id, UpdateAddressDto updateAddressDto);

    Address getById(String id);

    ReadAddressDto getAddressById(String id);

    List<Address> getAllByCity(City city);

    Page<ReadAddressDto> getAddressesByCity(String cityId);

    Page<ReadAddressDto> getAll(Pageable pageable);

    void delete(String id);
}
