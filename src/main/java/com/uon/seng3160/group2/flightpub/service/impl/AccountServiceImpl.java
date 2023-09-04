package com.uon.seng3160.group2.flightpub.service.impl;

import com.uon.seng3160.group2.flightpub.model.BookingModel;
import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.entity.Role;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.repository.RoleRepository;
import com.uon.seng3160.group2.flightpub.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository,
            PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserModel userModel) {
        Account account = new Account();
        account.setFirstName(userModel.getFirstName());
        account.setLastName(userModel.getLastName());
        account.setEmail(userModel.getEmail());

        // encrypt the password once we integrate spring security
        //
        Role role = this.getRole("ROLE_USER");

        account.setPassword(passwordEncoder.encode(userModel.getPassword()));

        account.setRoles(Arrays.asList(role));
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
        userModel.setFirstName(account.getFirstName());
        userModel.setLastName(account.getLastName());
        userModel.setEmail(account.getEmail());
        return userModel;
    }

    private Role getRole(String role) {
        Role roleObj = roleRepository.findByName(role);
        if (roleObj == null) {
            roleObj = new Role();
            roleObj.setName(role);
            roleRepository.save(roleObj);
        }
        return roleObj;
    }

    @Override
    public UserModel getAccountDetails(String username) {
        // Retrieve the user account based on the provided username (email)
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Convert the Account entity to a UserModel
        return convertEntityToModel(account);
    }

    @Override
    public UserModel updateAccountDetails(String username, UserModel updatedUserModel) {
        // Retrieve the user account based on the provided username (email)
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Update the user's details with the provided UserModel
        account.setFirstName(updatedUserModel.getFirstName());
        account.setLastName(updatedUserModel.getLastName());
        account.setEmail(updatedUserModel.getEmail());

        // Save the updated user account
        accountRepository.save(account);

        // Convert the updated Account entity to a UserModel and return it
        return convertEntityToModel(account);
    }

    @Override
    public boolean deleteAccount(String username) {
        // Retrieve the user account based on the provided username (email)
        Account account = accountRepository.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Delete the user account from the database
        accountRepository.delete(account);

        // Check if the account was deleted successfully (account should no longer exist)
        return !accountRepository.existsById(account.getId());
    }

    @Override
    public List<BookingModel> getUserBookings(String username) {
        // Implement logic to retrieve the bookings associated with the user based on username
        // You may need to create a BookingRepository and implement the necessary database queries
        // Return a list of BookingModel objects representing the user's bookings
        // Example:
        // List<Booking> bookings = bookingRepository.findByUserEmail(username);
        // return bookings.stream().map(this::convertBookingEntityToModel).collect(Collectors.toList());

        // For now, return an empty list as an example
        return Collections.emptyList();
    }

}