package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserMapper implements UserMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadUserProfileDto toReadUserProfileDto(User driver) {
        ReadUserProfileDto readUserProfileDto = new ReadUserProfileDto();

        readUserProfileDto.setId(driver.getId());
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
