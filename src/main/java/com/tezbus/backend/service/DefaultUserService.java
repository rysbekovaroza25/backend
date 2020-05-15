package com.tezbus.backend.service;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.entity.User;
import com.tezbus.backend.mapper.UserMapper;
import com.tezbus.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsMessageSenderService smsMessageSenderService;

    @Override
    @Transactional(readOnly = true)
    public ReadUserProfileDto getUserProfile(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("There is no User with name: " + id));

        return userMapper.toReadUserProfileDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new EntityNotFoundException("There is no Driver with name: " + id));
    }

}
