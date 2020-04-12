package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Submission;
import com.tezbus.backend.enums.SubmissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    List<Submission> findAllByStatus(SubmissionStatus status, Pageable pageable);
}
