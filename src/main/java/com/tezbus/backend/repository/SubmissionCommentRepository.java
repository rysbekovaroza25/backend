package com.tezbus.backend.repository;

import com.tezbus.backend.entity.SubmissionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SubmissionCommentRepository extends JpaRepository<SubmissionComment, UUID> {
}
