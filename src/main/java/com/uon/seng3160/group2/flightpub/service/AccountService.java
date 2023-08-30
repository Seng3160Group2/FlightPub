package com.uon.seng3160.group2.flightpub.service;

import com.uon.seng3160.group2.flightpub.dto.UserDto;
import com.uon.seng3160.group2.flightpub.entity.Account;

import java.util.List;

public interface AccountService {
    void saveAccount(UserDto userDto);

    Account findByEmail(String email);

    List<Account> findAllAccounts();
}