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
    void testFindByEmail() {
        // Create a sample email and user with the name "John Doe"
        String email = "john.doe@example.com";
        Account user = new Account();
        user.setEmail(email);
        user.setFirstName("John");
        user.setLastName("Doe");

        // Mock the repository method
        when(accountRepository.findByEmail(email)).thenReturn(user);

        // Test the findByEmail method
        Account result = accountService.findByEmail(email);

        // Verify that the method was called and the result matches the user
        verify(accountRepository, times(1)).findByEmail(email);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testGetAccountDetails() {
        // Create a sample email and user with the name "John Doe"
        String email = "john.doe@example.com";
        Account user = new Account();
        user.setEmail(email);
        user.setFirstName("John");
        user.setLastName("Doe");

        // Mock the repository method
        when(accountRepository.findByEmail(email)).thenReturn(user);

        // Test the getAccountDetails method
        UserModel userModel = accountService.getAccountDetails(email);

        // Verify that the method was called and the result matches the user
        verify(accountRepository, times(1)).findByEmail(email);
        assertEquals("John", userModel.getFirstName());
        assertEquals("Doe", userModel.getLastName());
    }

    @Test
    void testUpdateAccountDetails() {
        // Create a sample email and UserModel with updated data for "John Doe"
        String email = "john.doe@example.com";
        UserModel updatedUserModel = new UserModel();
        updatedUserModel.setFirstName("John");
        updatedUserModel.setLastName("Doe");

        // Create a sample user with the name "John Doe"
        Account user = new Account();
        user.setEmail(email);
        user.setFirstName("John");
        user.setLastName("Doe");

        // Mock the repository method
        when(accountRepository.findByEmail(email)).thenReturn(user);

        // Test the updateAccountDetails method
        UserModel updatedModel = accountService.updateAccountDetails(email, updatedUserModel);

        // Verify that the method was called, and the updated data is reflected in the result
        verify(accountRepository, times(1)).findByEmail(email);
        assertEquals("John", updatedModel.getFirstName());
        assertEquals("Doe", updatedModel.getLastName());
    }
}
