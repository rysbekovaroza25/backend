package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateDriverDto;
import com.tezbus.backend.dto.ReadDriverDto;
import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.entity.Driver;
import com.tezbus.backend.mapper.DriverMapper;
import com.tezbus.backend.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultDriverService implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private SmsMessageSenderService smsMessageSenderService;

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

    @Override
    @Transactional
    public ReadDriverDto create(CreateDriverDto createDriverDto) {
        Driver driver = new Driver();
        driver.setUserId(UUID.fromString(createDriverDto.getUserId()));
        driver.setFirstName(createDriverDto.getFirstName());
        driver.setLastName(createDriverDto.getLastName());
        driver.setPhoneNumber(createDriverDto.getPhoneNumber());
        driver.setCreatedAt(ZonedDateTime.now());

        Driver savedDriver = driverRepository.save(driver);

        smsMessageSenderService.sendOnDriverRegistration(savedDriver);

        return driverMapper.toReadDriverDto(savedDriver);
    }


}
