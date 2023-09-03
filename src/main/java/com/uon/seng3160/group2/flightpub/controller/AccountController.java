package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uon.seng3160.group2.flightpub.model.UserModel;



// import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("")
    public String exampleOne(Model model) {
        // model.addAttribute("name", name);
        UserModel user = new UserModel();
        model.addAttribute("account", user);
        return "account";
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
