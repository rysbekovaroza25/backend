package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DriverRepository extends JpaRepository<Driver, UUID> {
	Driver findByFirstName(String firstName);
	Driver findByFirstNameOrLastName(String firstName, String lastName);
	Driver findByUserId(String userId);
}
