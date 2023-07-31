package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello/")
public class HelloController {

    // enter http://localhost:8080/hello/path/anton into your browser
    @GetMapping("path/{name}")
    public String exampleOne(@PathVariable String name, Model model) {
        model.addAttribute("name", name);

        return "path-based";
    }

    // enter http://localhost:8080/hello/query?name=anton into your browser
    @GetMapping("query")
    public ModelAndView exampleTwo(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("query-based");
        modelAndView.addObject("name", name);

        return modelAndView;
    }
}
