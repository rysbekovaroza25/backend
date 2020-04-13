package com.tezbus.backend.pageable;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemSearchRequest {
    private UUID fromCity;
    private UUID toCity;
}