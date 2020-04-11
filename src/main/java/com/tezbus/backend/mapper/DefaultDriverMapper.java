package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadDriverDto;
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

        return readDriverDto;
    }
}
