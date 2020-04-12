package com.tezbus.backend.pageable;

public class TripPageRequest extends PageRequest {
    public TripPageRequest(int offset, int limit, String sortBy) {
        super(offset, limit, sortBy);
    }
}
