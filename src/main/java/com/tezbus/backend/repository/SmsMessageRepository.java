package com.tezbus.backend.repository;

import com.tezbus.backend.entity.SmsMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsMessageRepository extends JpaRepository<SmsMessage, String> {
}
