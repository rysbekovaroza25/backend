package com.tezbus.backend.repository;

import com.tezbus.backend.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, UUID> {

}
