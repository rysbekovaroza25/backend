package com.tezbus.backend.service;

import com.tezbus.backend.dto.CreateItemDto;
import com.tezbus.backend.dto.ReadItemDto;
import com.tezbus.backend.pageable.ItemSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    ReadItemDto create(CreateItemDto createItemDto);

    Page<ReadItemDto> getAll(ItemSearchRequest itemSearchRequest, Pageable pageable);

    ReadItemDto assignByUser(String userId, String id);
}
