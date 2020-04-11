package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadDriverDto;
import com.tezbus.backend.entity.Driver;

public interface DriverMapper {
    ReadDriverDto toReadDriverDto(Driver driver);
}
