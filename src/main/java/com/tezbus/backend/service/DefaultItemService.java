package com.tezbus.backend.service;

import com.tezbus.backend.dto.AssignByDriverDto;
import com.tezbus.backend.dto.CreateItemDto;
import com.tezbus.backend.dto.ReadItemDto;
import com.tezbus.backend.entity.City;
import com.tezbus.backend.entity.Driver;
import com.tezbus.backend.entity.Item;
import com.tezbus.backend.mapper.ItemMapper;
import com.tezbus.backend.pageable.ItemSearchRequest;
import com.tezbus.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultItemService implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    @Transactional
    public ReadItemDto create(CreateItemDto createItemDto) {
        City fromCity = cityService.getById(createItemDto.getFromCity());
        City toCity = cityService.getById(createItemDto.getToCity());

        Item item = new Item();
        item.setFirstName(createItemDto.getFirstName());
        item.setLastName(createItemDto.getLastName());
        item.setDescription(createItemDto.getDescription());
        item.setFromCity(fromCity);
        item.setToCity(toCity);
        item.setEmail(createItemDto.getEmail());
        item.setPhoneNumber(createItemDto.getPhoneNumber());
        item.setIsActive(true);
        item.setAssignedDriver(null);
        item.setCreatedAt(ZonedDateTime.now());
        item.setModifiedAt(ZonedDateTime.now());

        Item savedItem = itemRepository.save(item);

        return itemMapper.toReadItemDto(savedItem);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReadItemDto> getAll(ItemSearchRequest itemSearchRequest, Pageable pageable) {
        City fromCity = cityService.getById(itemSearchRequest.getFromCity());
        City toCity = cityService.getById(itemSearchRequest.getToCity());

        Page<Item> items = itemRepository.findByFromCityAndToCityAndIsActive(fromCity, toCity, true, pageable);

        return new PageImpl<>(items.stream().map(it -> itemMapper.toReadItemDto(it)).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public ReadItemDto assignByDriver(AssignByDriverDto assignByDriverDto, UUID id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item = optionalItem.orElseThrow(() -> new EntityNotFoundException("There is no Item with id: " + id));

        Driver driver = driverService.getById(assignByDriverDto.getDriverId());

        item.setAssignedDriver(driver);
        item.setModifiedAt(ZonedDateTime.now());
        item.setIsActive(false);

        Item updatedItem = itemRepository.save(item);

        return itemMapper.toReadItemDto(updatedItem);
    }

}
