package com.tezbus.backend.repository;

import com.tezbus.backend.entity.Address;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, String> {
    List<Trip> findByDepartureAddressInAndDestinationAddressInAndStartTimeGreaterThanEqualAndStartTimeLessThanEqual(
            List<Address> departuresAddresses,
            List<Address> destinationAddresses,
            ZonedDateTime from,
            ZonedDateTime to,
            Pageable pageable
    );

    List<Trip> findByUser(User user, Pageable pageable);
}
