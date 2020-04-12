package com.tezbus.backend.pageable;

public class ItemPageRequest extends PageRequest {
    public ItemPageRequest(int offset, int limit, String sortBy) {
        super(offset, limit, sortBy);
    }
}