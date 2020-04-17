package com.tezbus.backend.pageable;

public class AddressPageRequest extends PageRequest {
    public AddressPageRequest(int offset, int limit, String sortBy){
        super(offset, limit, sortBy);
    }
}
