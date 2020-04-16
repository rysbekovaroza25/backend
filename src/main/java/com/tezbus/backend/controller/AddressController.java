package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateAddressDto;
import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.dto.UpdateAddressDto;
import com.tezbus.backend.pageable.AddressPageRequest;
import com.tezbus.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ReadAddressDto create(@RequestBody CreateAddressDto createAddressDto){
        return addressService.create(createAddressDto);
    }

    @PutMapping("/{addressId}")
    public ReadAddressDto update(@PathVariable UUID addressId, @RequestBody UpdateAddressDto updateAddressDto){
        return addressService.update(addressId, updateAddressDto);
    }

    @GetMapping("/{addressId}")
    public ReadAddressDto getById(@PathVariable UUID addressId){
        return addressService.getAddressById(addressId);
    }

    @GetMapping("/city/{cityId}")
    public Page<ReadAddressDto> getAllByCityId(@PathVariable UUID cityId){
        return addressService.getAddressesByCity(cityId);
    }

    @GetMapping
    public Page<ReadAddressDto> getAll(AddressPageRequest addressPageRequest){
        return addressService.getAll(addressPageRequest);
    }

    @DeleteMapping("/{addressId}")
    public void delete(@PathVariable UUID addressId){
        addressService.delete(addressId);
    }
}
