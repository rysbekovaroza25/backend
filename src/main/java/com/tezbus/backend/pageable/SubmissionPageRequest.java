package com.tezbus.backend.pageable;

public class SubmissionPageRequest extends PageRequest {
    public SubmissionPageRequest(int offset, int limit, String sortBy) {
        super(offset, limit, sortBy);
    }

    public SubmissionPageRequest(int offset, int limit) {
        super(offset, limit);
    }
}