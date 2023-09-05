package com.uon.seng3160.group2.flightpub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uon.seng3160.group2.flightpub.model.BookingModel;
import com.uon.seng3160.group2.flightpub.model.UserModel;
import com.uon.seng3160.group2.flightpub.service.AccountService;

@Controller
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getdetails")
    public ResponseEntity<UserModel> getAccountDetails(Authentication authentication) {
        String username = authentication.getName();
        UserModel userModel = accountService.getAccountDetails(username);
        return ResponseEntity.ok(userModel);
    }

    @PutMapping("/updatedetails")
    public ResponseEntity<UserModel> updateAccountDetails(
            Authentication authentication,
            @RequestBody UserModel updatedUserModel) {
        String username = authentication.getName();
        UserModel updatedModel = accountService.updateAccountDetails(username, updatedUserModel);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(Authentication authentication) {
        String username = authentication.getName();
        boolean deleted = accountService.deleteAccount(username);
        if (deleted) {
            return ResponseEntity.ok("Account deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete account");
        }
    }

    @GetMapping("/viewbookings")
    public ResponseEntity<List<BookingModel>> viewBookings(Authentication authentication) {
        String username = authentication.getName();
        List<BookingModel> bookings = accountService.getUserBookings(username);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("edit-details")
    public String editDetails(Model model) {
        // model.addAttribute("name", name);
        UserModel user = new UserModel();
        model.addAttribute("account", user);
        return "edit-details";
    }

    @GetMapping("/flight-history")
    public String flightHistory(Model model) {
        return "flight-history";
    }

    @GetMapping("/current-bookings")
    public String currentBookings(Model model) {
        return "current-bookings";
    }
}
