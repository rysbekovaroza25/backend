package com.tezbus.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SendPassengerDetailsDto {

    @NotNull
    private String userId;

    @NotNull
    private String passengerName;

    @NotNull
    private String passengerPhone;

    @NotNull
    private Integer desiredSeatCount;

    private String note;
}
