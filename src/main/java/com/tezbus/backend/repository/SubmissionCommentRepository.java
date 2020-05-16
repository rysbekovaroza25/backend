package com.tezbus.backend.repository;

import com.tezbus.backend.entity.SubmissionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionCommentRepository extends JpaRepository<SubmissionComment, String> {
}
