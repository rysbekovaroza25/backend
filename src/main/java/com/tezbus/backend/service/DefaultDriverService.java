package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.entity.Driver;
import com.tezbus.backend.mapper.DriverMapper;
import com.tezbus.backend.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultDriverService implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Override
    @Transactional(readOnly = true)
    public ReadDriverProfileDto getDriverProfile(UUID driverId) {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        Driver driver = optionalDriver.orElseThrow(() -> new EntityNotFoundException("There is no Driver with name: " + driverId));

        return driverMapper.toReadDriverProfileDto(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getById(UUID id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        return optionalDriver.orElseThrow(() -> new EntityNotFoundException("There is no Driver with name: " + id));
    }


}
