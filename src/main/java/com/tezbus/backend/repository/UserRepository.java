package com.tezbus.backend.repository;

import com.tezbus.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
	User findByFirstName(String firstName);
	User findByFirstNameOrLastName(String firstName, String lastName);
}
