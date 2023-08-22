package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



// import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/")
    public String exampleOne(Model model) {
        // model.addAttribute("name", name);

        return "account";
    }
}
