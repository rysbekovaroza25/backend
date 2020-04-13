package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadDriverDto;
import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.entity.Driver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultDriverMapper implements DriverMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadDriverDto toReadDriverDto(Driver driver) {
        ReadDriverDto readDriverDto = new ReadDriverDto();
        readDriverDto.setId(driver.getId());
        readDriverDto.setFirstName(driver.getFirstName());
        readDriverDto.setLastName(driver.getLastName());
        readDriverDto.setGender(driver.getGender());
        readDriverDto.setEmail(driver.getEmail());
        readDriverDto.setPhoneNumber(driver.getPhoneNumber());
        readDriverDto.setTransportType(driver.getTransportType());
        readDriverDto.setTransportModel(driver.getTransportModel());
        readDriverDto.setTransportNumber(driver.getTransportNumber());
        readDriverDto.setCreatedAt(driver.getCreatedAt());
        readDriverDto.setModifiedAt(driver.getModifiedAt());

        return readDriverDto;
    }

    @Override
    @Transactional(readOnly = true)
    public ReadDriverProfileDto toReadDriverProfileDto(Driver driver) {
        ReadDriverProfileDto readDriverProfileDto = new ReadDriverProfileDto();
        readDriverProfileDto.setId(driver.getId());
        readDriverProfileDto.setUserId(driver.getUserId());
        readDriverProfileDto.setFirstName(driver.getFirstName());
        readDriverProfileDto.setLastName(driver.getLastName());
        readDriverProfileDto.setBirthDate(driver.getBirthDate());
        readDriverProfileDto.setGender(driver.getGender());
        readDriverProfileDto.setPhoneNumber(driver.getPhoneNumber());
        readDriverProfileDto.setEmail(driver.getEmail());
        readDriverProfileDto.setTransportType(driver.getTransportType());
        readDriverProfileDto.setTransportModel(driver.getTransportModel());
        readDriverProfileDto.setTransportNumber(driver.getTransportNumber());
        readDriverProfileDto.setCreatedAt(driver.getCreatedAt());
        readDriverProfileDto.setModifiedAt(driver.getModifiedAt());

        return readDriverProfileDto;
    }
}
