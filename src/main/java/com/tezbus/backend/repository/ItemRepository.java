package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findByFromCityContainingIgnoreCaseAndToCityContainingIgnoreCase(String fromCity, String toCity, Pageable pageable);
}
