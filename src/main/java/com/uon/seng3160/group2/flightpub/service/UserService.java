package com.uon.seng3160.group2.flightpub.service;

import com.uon.seng3160.group2.flightpub.entity.User;
import com.uon.seng3160.group2.flightpub.model.UserModel;

import java.util.List;

public interface UserService {
    void saveUser(UserModel userDto);

    User findByEmail(String email);

    List<UserModel> findAllUsers();
}