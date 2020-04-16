package com.tezbus.backend.pageable;

public class CityPageRequest extends PageRequest {
    public CityPageRequest(int offset, int limit, String sortBy){
        super(offset, limit, sortBy);
    }
}
