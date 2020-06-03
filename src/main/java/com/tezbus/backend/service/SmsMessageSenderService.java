package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadSmsMessageDto;
import com.tezbus.backend.dto.SendPassengerDetailsDto;
import com.tezbus.backend.entity.Trip;
import com.tezbus.backend.entity.User;

public interface SmsMessageSenderService {
    ReadSmsMessageDto sendOnUserRegistration(User user);

    ReadSmsMessageDto sendOnPassengerReservation(Trip trip, SendPassengerDetailsDto sendPassengerDetailsDto, User user);
}
