package com.tezbus.backend.pageable;

public class ContactMessagePageRequest extends PageRequest {
    public ContactMessagePageRequest(int offset, int limit, String sortBy) {
        super(offset, limit, sortBy);
    }
}
