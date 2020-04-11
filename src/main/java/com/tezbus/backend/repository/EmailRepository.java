package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
