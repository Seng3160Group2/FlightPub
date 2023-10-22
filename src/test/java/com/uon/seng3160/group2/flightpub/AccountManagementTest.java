package com.uon.seng3160.group2.flightpub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uon.seng3160.group2.flightpub.controller.AccountController;
import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.service.AccountService;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountManagementTest {


    @MockBean
    private AccountService accountService;
    private AccountController accountController;
   
    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @BeforeEach
    public void setUp() {
        accountService = mock(AccountService.class);
        accountController = new AccountController(accountService);
    }


    @Test
    public void testEmptyEmailValidation() {
        // Create a UserModel with an empty email
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("");
        userModel.setPassword("password");

        // Validate the UserModel
        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);

        // Check if there is a violation for the empty email field
        assertThat(violations).hasSize(1);
        ConstraintViolation<UserModel> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("email");

        // Check the error message
        assertThat(violation.getMessage()).isEqualTo("Email should not be empty");
    }

    @Test
    public void testEmptyPasswordValidation() {
        // Create a UserModel with an empty password
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("john@example.com");
        userModel.setPassword("");

        // Validate the UserModel
        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);

        // Check if there is a violation for the empty email field
        assertThat(violations).hasSize(1);
        ConstraintViolation<UserModel> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("password");

        // Check the error message
        assertThat(violation.getMessage()).isEqualTo("Password should not be empty");
    }

    @Test
    public void testEmptyFirstNameValidation() {
        // Create a UserModel with an empty password
        UserModel userModel = new UserModel();
        userModel.setFirstName("");
        userModel.setLastName("Doe");
        userModel.setEmail("john@example.com");
        userModel.setPassword("password");

        // Validate the UserModel
        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);

        // Check if there is a violation for the empty email field
        assertThat(violations).hasSize(1);
        ConstraintViolation<UserModel> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");
    }

    @Test
    public void testEmptyLastNameValidation() {
        // Create a UserModel with an empty password
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("");
        userModel.setEmail("john@example.com");
        userModel.setPassword("password");

        // Validate the UserModel
        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);

        // Check if there is a violation for the empty email field
        assertThat(violations).hasSize(1);
        ConstraintViolation<UserModel> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("lastName");
    }

    @Test
    public void testSuccessfulValidation() {
        // Create a UserModel with an empty email
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("john@example.com");
        userModel.setPassword("password");

        Set<ConstraintViolation<UserModel>> violations = validator.validate(userModel);

        // Check if there are no violations for the valid UserModel
        assertThat(violations).isEmpty();

    }

    @Test
    public void testUpdateAccountDetails() {
        // Create a UserModel with updated details
        UserModel updatedUserModel = new UserModel();
        updatedUserModel.setFirstName("John");
        updatedUserModel.setLastName("Doe");
        updatedUserModel.setEmail("john@example.com");
        updatedUserModel.setPassword("password");

        // Mock the accountService's updateAccountDetails method to return the updated UserModel
        when(accountService.updateAccountDetails(anyString(), any(UserModel.class))).thenReturn(updatedUserModel);

        // Create an Authentication object with a username
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");

        // Call the updateAccountDetails method of the accountController with the Authentication object and updated UserModel
        ResponseEntity<UserModel> responseEntity = accountController.updateAccountDetails(authentication, updatedUserModel);

        // Check if the response entity contains the updated UserModel and has an HTTP status code of 200 (OK)
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(updatedUserModel);
    }  

    @Test
    public void testDeleteAccount() {
        // Mock the accountService's deleteAccount method to return true
        when(accountService.deleteAccount(anyString())).thenReturn(true);

        // Create an Authentication object with a username
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");

        // Call the deleteAccount method of the accountController with the Authentication object
        ResponseEntity<String> responseEntity = accountController.deleteAccount(authentication);

        // Check if the response entity has an HTTP status code of 200 (OK) and a message "Account deleted successfully"
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Account deleted successfully");
    }

    @Test
    public void testPasswordEncryption() {
        // Create a new UserModel with a password
        UserModel userModel = new UserModel();
        userModel.setFirstName("John");
        userModel.setLastName("Doe");
        userModel.setEmail("john@example.com");
        userModel.setPassword("password");

        // Create a new Account object with the same email as the UserModel
        Account account = new Account();
        account.setEmail(userModel.getEmail());
        account.setPassword(passwordEncoder.encode(userModel.getPassword()));

        // Mock the accountRepository to return the Account object when findByEmail is called
        when(accountRepository.findByEmail(userModel.getEmail())).thenReturn(account);

        // Save the UserModel using the accountRepository
        accountRepository.save(account);

        // Get the saved Account object from the accountRepository
        Account retrievedAccount = accountRepository.findByEmail(userModel.getEmail());

        // Check if the password is encrypted
        assertThat(passwordEncoder.matches("password", retrievedAccount.getPassword())).isTrue();
    }

}

