package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultAddressMapper implements AddressMapper {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Transactional(readOnly = true)
    public ReadAddressDto toReadAddressDto(Address address) {
        ReadAddressDto readAddressDto = new ReadAddressDto();
        readAddressDto.setId(address.getId());
        readAddressDto.setStreetName(address.getStreetName());
        readAddressDto.setCity(cityMapper.toReadCityDto(address.getCity()));

        return readAddressDto;
    }
}
