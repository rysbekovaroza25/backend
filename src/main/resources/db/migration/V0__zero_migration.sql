create table drivers
(
    id               uuid         not null primary key,
    birth_date       timestamp,
    email            varchar(255),
    first_name       varchar(255) not null,
    gender           varchar(255),
    last_name        varchar(255) not null,
    phone_number     varchar(255) not null,
    transport_model  varchar(255),
    transport_number varchar(255),
    transport_type   varchar(255),
    user_id          uuid         not null,
    created_at       timestamp    not null,
    modified_at      timestamp
);

create table cities
(
    id          uuid         not null primary key,
    country     varchar(255) not null,
    name        varchar(255) not null,
    time_zone   varchar(255) not null,
    is_deleted  boolean      not null,
    created_at  timestamp    not null,
    modified_at timestamp
);

create table addresses
(
    id          uuid         not null primary key,
    is_deleted  boolean      not null,
    street_name varchar(255) not null,
    city_id     uuid references cities (id),
    created_at  timestamp    not null,
    modified_at timestamp,
    unique (city_id, street_name)
);

create table trips
(
    id                         uuid           not null primary key,
    available_passengers_count integer        not null,
    caption                    varchar(255)   not null,
    duration                   bigint         not null,
    end_time                   timestamp      not null,
    passengers_capacity        integer        not null,
    price                      numeric(19, 2) not null,
    start_time                 timestamp      not null,
    transport_type             integer        not null,
    departure_address_id       uuid           not null references addresses (id),
    destination_address_id     uuid           not null references addresses (id),
    driver_id                  uuid           not null references drivers (id),
    is_deleted                 boolean        not null,
    created_at                 timestamp      not null,
    modified_at                timestamp
);

create table submissions
(
    id                            uuid         not null primary key,
    birth_date                    timestamp    not null,
    driver_license_card_back_url  varchar(255) not null,
    driver_license_card_front_url varchar(255) not null,
    email                         varchar(255) not null,
    gender                        varchar(6)   not null,
    status                        varchar(8)   not null,
    transport_model               varchar(255) not null,
    transport_number              varchar(20)  not null,
    transport_type                varchar(20)  not null,
    driver_id                     uuid references drivers(id),
    created_at                    timestamp    not null,
    modified_at                   timestamp
);

create table submission_comments
(
    id            uuid         not null primary key,
    comment       varchar(255) not null,
    submission_id uuid         not null references submissions (id),
    created_at    timestamp    not null,
    modified_at   timestamp
);

create table sms_messages
(
    id                uuid        not null primary key,
    content           text        not null,
    notification_type varchar(255),
    phone_number      varchar(20) not null,
    trip_id           varchar(255),
    created_at        timestamp   not null,
    modified_at       timestamp
);

create table emails
(
    id              uuid         not null primary key,
    body            text         not null,
    recipient_email varchar(255) not null,
    title           varchar(255) not null,
    trip_id         varchar(255),
    created_at      timestamp    not null,
    modified_at     timestamp
);

create table items
(
    id                 uuid         not null primary key,
    description        varchar(255) not null,
    email              varchar(255) not null,
    first_name         varchar(255) not null,
    is_active          boolean      not null,
    last_name          varchar(255) not null,
    phone_number       varchar(255) not null,
    assigned_driver_id uuid references drivers(id),
    from_city_id       uuid not null references cities(id),
    to_city_id         uuid not null references cities(id),
    created_at         timestamp    not null,
    modified_at        timestamp
);

create table contact_messages
(
    id          uuid         not null primary key,
    status      varchar(8)   not null,
    description text         not null,
    email       varchar(255) not null,
    name        varchar(255) not null,
    created_at  timestamp    not null,
    modified_at timestamp
);