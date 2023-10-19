package com.uon.seng3160.group2.flightpub.testing;

import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.entity.Role;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.security.CustomUserDetailsService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testLoadUserByUsername() {
        // Create a sample user
        String email = "john.doe@example.com";
        Account user = new Account();
        user.setEmail(email);
        user.setPassword("password");

        // Mock the repository method
        when(accountRepository.findByEmail(email)).thenReturn(user);

        // Test the loadUserByUsername method
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        // Verify that the method was called and the UserDetails matches the user
        verify(accountRepository, times(1)).findByEmail(email);
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        // Create a sample email that doesn't exist in the repository
        String email = "nonexistent@example.com";

        // Mock the repository method to return null (user not found)
        when(accountRepository.findByEmail(email)).thenReturn(null);

        // Test the loadUserByUsername method and expect a UsernameNotFoundException
        try {
            customUserDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            // UsernameNotFoundException is expected
        }

        // Verify that the method was called
        verify(accountRepository, times(1)).findByEmail(email);
    }

    @Test
    void testMapRolesToAuthorities() {
        // Create a sample role and user
        Role role = new Role();
        role.setName("ROLE_USER");
        Account user = new Account();
        user.setRoles(Collections.singletonList(role));

        // Test the mapRolesToAuthorities method
        Collection<? extends GrantedAuthority> authorities = customUserDetailsService.mapRolesToAuthorities(user.getRoles());

        // Verify that the authority is created from the role name
        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
    }
}
