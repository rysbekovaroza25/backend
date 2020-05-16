package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;

public interface UserService {
    ReadUserProfileDto getUserProfile(String id);

    User getById(String id);
}
