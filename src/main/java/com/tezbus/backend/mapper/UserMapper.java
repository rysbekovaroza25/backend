package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;

public interface UserMapper {
    ReadUserProfileDto toReadUserProfileDto(User user);
}
