package com.uon.seng3160.group2.flightpub.service.impl;

import com.uon.seng3160.group2.flightpub.dto.UserDto;
import com.uon.seng3160.group2.flightpub.entity.Account;

import com.uon.seng3160.group2.flightpub.repository.RoleRepository;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAccount(UserDto userDto) {
        Account account = new Account();
        // TODO: why arent these two separate in the db?
        account.setName(userDto.getFirstName() + " " + userDto.getLastName());
        account.setEmail(userDto.getEmail());

        // encrypt the password once we integrate spring security
        // user.setPassword(userDto.getPassword());
        account.setPassword(passwordEncoder.encode(userDto.getPassword()));

        accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
}