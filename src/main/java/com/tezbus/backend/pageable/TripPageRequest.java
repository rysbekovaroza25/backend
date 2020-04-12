package com.tezbus.backend.pageable;

public class TripPageRequest extends PageRequest {
    public TripPageRequest(int offset, int limit, String sortBy) {
        super(offset, limit, sortBy);
    }

    public TripPageRequest(int offset, int limit) {
        super(offset, limit);
    }
}
