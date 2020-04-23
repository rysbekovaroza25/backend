package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateDriverDto;
import com.tezbus.backend.dto.ReadDriverDto;
import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/{driverId}/profile")
    public ReadDriverProfileDto getDriverProfile(@PathVariable UUID driverId) {
        return driverService.getDriverProfile(driverId);
    }

    @PostMapping
    public ReadDriverDto create(@RequestBody @Validated CreateDriverDto createDriverDto) {
        return driverService.create(createDriverDto);
    }
}
