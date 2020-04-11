package com.tezbus.backend.pageable;

import com.tezbus.backend.enums.SubmissionStatus;
import lombok.Data;

@Data
public class SubmissionSearchRequest {
    private SubmissionStatus status;
}
