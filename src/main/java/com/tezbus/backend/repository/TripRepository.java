package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.Trip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    List<Trip> findByDepartureAddressInAndDestinationAddressInAndStartTimeGreaterThanEqualAndStartTimeLessThanEqual(
            List<Address> departuresAddresses,
            List<Address> destinationAddresses,
            ZonedDateTime from,
            ZonedDateTime to,
            Pageable pageable
    );

    List<Trip> findByDriverId(UUID driverId, Pageable pageable);
}
