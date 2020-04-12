package com.tezbus.backend.controller;

import com.tezbus.backend.dto.ReadDriverProfileDto;
import com.tezbus.backend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
