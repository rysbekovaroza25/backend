package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Submission;
import com.tezbus.backend.enums.SubmissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    Page<Submission> findAllByStatus(SubmissionStatus status, Pageable pageable);
}
