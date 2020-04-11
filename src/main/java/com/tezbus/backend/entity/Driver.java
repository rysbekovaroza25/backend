package com.tezbus.backend.entity;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "transport_model", nullable = false)
    private String transportModel;

    @Column(name = "transport_number", nullable = false)
    private String transportNumber;

    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "modified_at")
    private ZonedDateTime modifiedAt;

}
