package com.tezbus.backend.entity;

import com.tezbus.backend.enums.Gender;
import com.tezbus.backend.enums.SubmissionStatus;
import com.tezbus.backend.enums.TransportType;
import lombok.Data;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6, nullable = false)
    private Gender gender;

    @Column(name = "driver_license_card_front_url", nullable = false)
    private String driverLicenseCardFrontUrl;

    @Column(name = "driver_license_card_back_url", nullable = false)
    private String driverLicenseCardBackUrl;

    @Column(name = "transport_model", nullable = false)
    private String transportModel;

    @Column(name = "transport_number", length = 20, nullable = false)
    private String transportNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type", length = 20, nullable = false)
    private TransportType transportType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8, nullable = false)
    private SubmissionStatus status;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private ZonedDateTime modifiedAt;

    @OneToMany(mappedBy = "submission")
    private List<SubmissionComment> submissionComments = Collections.emptyList();

}
