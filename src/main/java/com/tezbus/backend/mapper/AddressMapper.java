package com.tezbus.backend.mapper;

import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.entity.Address;

public interface AddressMapper {
    ReadAddressDto toReadAddressDto(Address address);
}
