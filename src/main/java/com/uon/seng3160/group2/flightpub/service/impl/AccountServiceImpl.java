package com.uon.seng3160.group2.flightpub.service.impl;

import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository,
            PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserModel userModel) {
        Account account = new Account();
        // TODO: why arent these two separate in the db?
        account.setName(userModel.getFirstName() + " " + userModel.getLastName());
        account.setEmail(userModel.getEmail());

        // encrypt the password once we integrate spring security
        // user.setPassword(userDto.getPassword());
        account.setPassword(passwordEncoder.encode(userModel.getPassword()));

        accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<UserModel> findAllUsers() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> convertEntityToModel(account)).collect(Collectors.toList());
    }

    private UserModel convertEntityToModel(Account account) {
        UserModel userModel = new UserModel();
        String[] name = account.getName().split(" ");
        userModel.setFirstName(name[0]);
        userModel.setLastName(name[1]);
        userModel.setEmail(account.getEmail());
        return userModel;
    }

}