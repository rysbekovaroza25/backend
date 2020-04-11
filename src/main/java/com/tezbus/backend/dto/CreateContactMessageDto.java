package com.tezbus.backend.dto;

import lombok.Data;

@Data
public class CreateContactMessageDto {
    private String name;
    private String email;
    private String description;
}
