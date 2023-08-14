package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/styles/")
public class StylesController {

    // enter http://localhost:8080/hello/path/anton into your browser
    @GetMapping("")
    public String exampleOne(Model model) {
        model.addAttribute("example", null);

        return "example";
    }
}
