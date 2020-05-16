package com.tezbus.backend.controller;

import com.tezbus.backend.dto.CreateAddressDto;
import com.tezbus.backend.dto.ReadAddressDto;
import com.tezbus.backend.dto.UpdateAddressDto;
import com.tezbus.backend.pageable.AddressPageRequest;
import com.tezbus.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ReadAddressDto create(@RequestBody @Validated CreateAddressDto createAddressDto) {
        return addressService.create(createAddressDto);
    }

    @PutMapping("/{id}")
    public ReadAddressDto update(@PathVariable String id, @RequestBody @Validated UpdateAddressDto updateAddressDto) {
        return addressService.update(id, updateAddressDto);
    }

    @GetMapping("/{id}")
    public ReadAddressDto getById(@PathVariable String id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/getByCity/{cityId}")
    public Page<ReadAddressDto> getAllByCityId(@PathVariable String cityId) {
        return addressService.getAddressesByCity(cityId);
    }

    @GetMapping
    public Page<ReadAddressDto> getAll(AddressPageRequest addressPageRequest) {
        return addressService.getAll(addressPageRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        addressService.delete(id);
    }
}
