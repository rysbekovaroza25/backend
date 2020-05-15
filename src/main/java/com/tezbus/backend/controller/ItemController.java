package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateItemDto;
import com.tezbus.backend.dto.ReadItemDto;
import com.tezbus.backend.pageable.ItemPageRequest;
import com.tezbus.backend.pageable.ItemSearchRequest;
import com.tezbus.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ReadItemDto create(@RequestBody @Validated CreateItemDto createItemDto) {
        return itemService.create(createItemDto);
    }

    @GetMapping
    public Page<ReadItemDto> getAll(ItemSearchRequest itemSearchRequest, ItemPageRequest itemPageRequest) {
        return itemService.getAll(itemSearchRequest, itemPageRequest);
    }

    @PutMapping("/{id}/assignByUser/{userId}")
    public ReadItemDto assignByUser(@PathVariable String userId, @PathVariable UUID id) {
        return itemService.assignByUser(userId, id);
    }
}
