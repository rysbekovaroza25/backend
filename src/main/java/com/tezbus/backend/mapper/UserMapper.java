package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadUserDto;
import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;

public interface UserMapper {
    ReadUserDto toReadUserDto(User user);
    ReadUserProfileDto toReadUserProfileDto(User user);
}
