package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.entity.Driver;

import java.util.UUID;

public interface DriverService {
    ReadDriverProfileDto getDriverProfile(UUID driverId);
    Driver getById(UUID id);
}
