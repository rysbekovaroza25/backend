package com.tezbus.backend.repository;

import com.tezbus.backend.entity.City;
import com.tezbus.backend.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {
    Page<Item> findByFromCityAndToCityAndIsActive(City fromCity, City toCity, boolean isActive, Pageable pageable);
}
