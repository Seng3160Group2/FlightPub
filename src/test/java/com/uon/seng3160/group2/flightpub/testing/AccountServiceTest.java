package com.uon.seng3160.group2.flightpub.testing;

import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.service.impl.AccountServiceImpl;
import com.uon.seng3160.group2.flightpub.entity.Account;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void testSaveUser() {
        // Create a UserModel and test the saveUser method
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("john.doe@example.com");
        userModel.setPassword("password");

        accountService.saveUser(userModel);

        // Add your verification/assertion logic here
    }

    @Test
    void testFindByEmail() {
        // Create a sample email and user
        String email = "john.doe@example.com";
        Account user = new Account();
        user.setEmail(email);

        // Mock the repository method
        when(accountRepository.findByEmail(email)).thenReturn(user);

        // Test the findByEmail method
        Account result = accountService.findByEmail(email);

        // Verify that the method was called and the result matches the user
        verify(accountRepository, times(1)).findByEmail(email);
        assertEquals(user, result);
    }

    @Test
    void testFindAllUsers() {
        // Mock the repository method
        when(accountRepository.findAll()).thenReturn(Collections.emptyList());

        // Test the findAllUsers method
        List<UserModel> users = accountService.findAllUsers();

        // Verify that the method was called and the list is empty
        verify(accountRepository, times(1)).findAll();
        assertEquals(0, users.size());
    }
}
