package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/flights/")
public class FlightController {

    @GetMapping()
    public String getFlight(@RequestParam int id, Model model) {

        return "flight";
    }
}
