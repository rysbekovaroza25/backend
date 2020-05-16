package com.tezbus.backend.pageable;

import lombok.Data;

@Data
public class ItemSearchRequest {
    private String fromCity;
    private String toCity;
}