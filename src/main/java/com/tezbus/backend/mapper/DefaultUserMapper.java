package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserMapper implements UserMapper {

    @Override
    @Transactional(readOnly = true)
    public ReadUserProfileDto toReadUserProfileDto(User user) {
        ReadUserProfileDto readUserProfileDto = new ReadUserProfileDto();

        readUserProfileDto.setId(user.getId());
        readUserProfileDto.setFirstName(user.getFirstName());
        readUserProfileDto.setLastName(user.getLastName());
        readUserProfileDto.setBirthDate(user.getBirthDate());
        readUserProfileDto.setGender(user.getGender());
        readUserProfileDto.setPhoneNumber(user.getPhoneNumber());
        readUserProfileDto.setEmail(user.getEmail());
        readUserProfileDto.setTransportType(user.getTransportType());
        readUserProfileDto.setTransportModel(user.getTransportModel());
        readUserProfileDto.setTransportNumber(user.getTransportNumber());
        readUserProfileDto.setCreatedAt(user.getCreatedAt());
        readUserProfileDto.setModifiedAt(user.getModifiedAt());

        return readUserProfileDto;
    }
}
