package com.uon.seng3160.group2.flightpub.service;

import com.uon.seng3160.group2.flightpub.dto.UserDto;
import com.uon.seng3160.group2.flightpub.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}