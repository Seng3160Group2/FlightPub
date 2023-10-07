package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uon.seng3160.group2.flightpub.service.AccountService;
import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.model.UserModel;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class AuthController {

    private AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserModel user,
            BindingResult result,
            Model model) {
        System.out.println("Registration method called"); // Debug statement

        Account existing = accountService.findByEmail(user.getEmail());
        if (existing != null) {
            System.out.println("User already exists with email: " + user.getEmail()); // Debug statement
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            System.out.println("Errors found: " + result.getAllErrors()); // Debug statement
            model.addAttribute("user", user);
            return "register";
        }

        System.out.println("User data received: " + user); // Debug statement

        accountService.saveUser(user);

        System.out.println("User saved successfully"); // Debug statement

        return "redirect:/register?success";
    }

}
