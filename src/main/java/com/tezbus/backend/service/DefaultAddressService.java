package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateAddressDto;
import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.dto.UpdateAddressDto;
import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.mapper.AddressMapper;
import com.tezbus.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultAddressService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private CityService cityService;

    @Override
    @Transactional
    public ReadAddressDto create(CreateAddressDto createAddressDto) {
        City city = cityService.getById(createAddressDto.getCityId());
        Address address = new Address();
        address.setStreetName(createAddressDto.getStreetName());
        address.setCity(city);
        address.setCreatedAt(ZonedDateTime.now());
        address.setModifiedAt(ZonedDateTime.now());
        address.setDeleted(false);

        Address savedAddress = addressRepository.save(address);

        return addressMapper.toReadAddressDto(savedAddress);
    }

    @Override
    @Transactional
    public ReadAddressDto update(UUID id, UpdateAddressDto updateAddressDto) {
        City city = cityService.getById(updateAddressDto.getCityId());
        Address address = getById(id);
        address.setStreetName(updateAddressDto.getStreetName());
        address.setCity(city);
        address.setModifiedAt(ZonedDateTime.now());

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toReadAddressDto(updatedAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public Address getById(UUID id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.orElseThrow(() -> new EntityNotFoundException("There is no Address with name: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public ReadAddressDto getAddressById(UUID id) {
        return addressMapper.toReadAddressDto(getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAllByCity(City city) {
        return addressRepository.findByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadAddressDto> getAddressesByCity(UUID cityId) {
        City city = cityService.getById(cityId);
        return new PageImpl<>(addressRepository.findByCity(city)
                .stream()
                .filter(address -> !address.isDeleted())
                .map(address -> addressMapper.toReadAddressDto(address))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadAddressDto> getAll(Pageable pageable) {
        return new PageImpl<>(addressRepository.findAll(pageable)
                .stream()
                .filter(address -> !address.isDeleted())
                .map(address -> addressMapper.toReadAddressDto(address))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Address address = getById(id);
        address.setModifiedAt(ZonedDateTime.now());
        address.setDeleted(true);

        addressRepository.save(address);
    }
}
