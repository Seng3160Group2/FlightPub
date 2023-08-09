package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uon.seng3160.group2.flightpub.formatter.LocalDateTimeFormatter;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new LocalDateTimeFormatter());
    }

    @GetMapping("/search")
    public String showSearchForm(Model model, BindingResult bindingResult) {
        model.addAttribute("flightSearchForm", new FlightSearchForm());
        return "flight-search";
    }

    @GetMapping("/search-results")
    public String getFlightById(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "flight-search";
        }
        model.addAttribute("flightSearchForm", flightSearchForm);

        return "flight-search-results";
    }
}