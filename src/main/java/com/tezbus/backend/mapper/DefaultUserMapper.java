package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadUserDto;
import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserMapper implements UserMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadUserDto toReadDriverDto(User driver) {
        ReadUserDto readUserDto = new ReadUserDto();
        readUserDto.setId(driver.getId());
        readUserDto.setFirstName(driver.getFirstName());
        readUserDto.setLastName(driver.getLastName());
        readUserDto.setGender(driver.getGender());
        readUserDto.setEmail(driver.getEmail());
        readUserDto.setPhoneNumber(driver.getPhoneNumber());
        readUserDto.setTransportType(driver.getTransportType());
        readUserDto.setTransportModel(driver.getTransportModel());
        readUserDto.setTransportNumber(driver.getTransportNumber());
        readUserDto.setCreatedAt(driver.getCreatedAt());
        readUserDto.setModifiedAt(driver.getModifiedAt());

        return readUserDto;
    }

    @Override
    @Transactional(readOnly = true)
    public ReadUserProfileDto toReadDriverProfileDto(User driver) {
        ReadUserProfileDto readUserProfileDto = new ReadUserProfileDto();
        readUserProfileDto.setId(driver.getId());
        readUserProfileDto.setUserId(driver.getUserId());
        readUserProfileDto.setFirstName(driver.getFirstName());
        readUserProfileDto.setLastName(driver.getLastName());
        readUserProfileDto.setBirthDate(driver.getBirthDate());
        readUserProfileDto.setGender(driver.getGender());
        readUserProfileDto.setPhoneNumber(driver.getPhoneNumber());
        readUserProfileDto.setEmail(driver.getEmail());
        readUserProfileDto.setTransportType(driver.getTransportType());
        readUserProfileDto.setTransportModel(driver.getTransportModel());
        readUserProfileDto.setTransportNumber(driver.getTransportNumber());
        readUserProfileDto.setCreatedAt(driver.getCreatedAt());
        readUserProfileDto.setModifiedAt(driver.getModifiedAt());

        return readUserProfileDto;
    }
}
