package com.tezbus.backend.service;

import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultAddressService implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    public Address getById(UUID id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.orElseThrow(() -> new EntityNotFoundException("There is no Address with name: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAllByCity(City city) {
        return addressRepository.findByCity(city);
    }

}
