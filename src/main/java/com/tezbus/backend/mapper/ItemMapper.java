package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadItemDto;
import com.tezbus.backend.entity.Item;

public interface ItemMapper {
    ReadItemDto toReadItemDto(Item item);
}
