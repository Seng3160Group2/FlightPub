package com.uon.seng3160.group2.flightpub.service;

import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.entity.Account;

import java.util.List;

public interface AccountService {
    void saveUser(UserModel userModel);

    Account findByEmail(String email);

    List<UserModel> findAllUsers();
}