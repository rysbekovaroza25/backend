package com.tezbus.backend.entity;

import com.tezbus.backend.enums.TransportType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "departure_address_id", nullable = false)
    private Address departureAddress;

    @ManyToOne
    @JoinColumn(name = "destination_address_id", nullable = false)
    private Address destinationAddress;

    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private ZonedDateTime endTime;

    @Column(name = "duration", nullable = false)
    private long duration;

    @Column(name = "available_passengers_count", nullable = false)
    private int availablePassengersCount;

    @Column(name = "passengers_capacity", nullable = false)
    private int passengersCapacity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "caption", nullable = false)
    private String caption;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

}
