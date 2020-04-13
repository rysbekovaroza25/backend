package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadItemDto;
import com.tezbus.backend.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultItemMapper implements ItemMapper {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private DriverMapper driverMapper;

    @Override
    @Transactional(readOnly = true)
    public ReadItemDto toReadItemDto(Item item) {
        ReadItemDto itemDto = new ReadItemDto();
        itemDto.setId(item.getId());
        itemDto.setFirstName(item.getFirstName());
        itemDto.setLastName(item.getLastName());
        itemDto.setDescription(item.getDescription());
        itemDto.setFromCity(cityMapper.toReadCityDto(item.getFromCity()));
        itemDto.setToCity(cityMapper.toReadCityDto(item.getToCity()));
        itemDto.setEmail(item.getEmail());
        itemDto.setPhoneNumber(item.getPhoneNumber());
        itemDto.setIsActive(item.getIsActive());
        if (item.getAssignedDriver() != null) {
            itemDto.setAssignedDriverDto(driverMapper.toReadDriverDto(item.getAssignedDriver()));
        }
        itemDto.setCreatedAt(item.getCreatedAt());
        itemDto.setModifiedAt(item.getModifiedAt());

        return itemDto;
    }
}
