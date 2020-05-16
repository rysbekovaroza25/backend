package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, String> {
}
