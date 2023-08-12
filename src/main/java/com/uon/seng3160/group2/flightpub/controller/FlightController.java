package com.uon.seng3160.group2.flightpub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchForm;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    public FlightSearchService flightSearchService;

    @Autowired
    public Converter<Flight, FlightModel> conversionService;
    // @InitBinder
    // public void initBinder(WebDataBinder binder) {
    // binder.addCustomFormatter(new LocalDateTimeFormatter());
    // }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("flightSearchForm", new FlightSearchForm());
        return "flight-search";
    }

    @GetMapping("/search-results")
    public String getFlightById(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "flight-search";
        }

        Optional<Flight> result = flightSearchService.getFlight(
                flightSearchForm.getAirlineCode(),
                flightSearchForm.getFlightNumber(),
                flightSearchForm.getDepartureTime());

        if (result.isEmpty()) {
            model.addAttribute("flight", null);

            return "flight-search-results";
        }
        Flight flight = result.get();
        FlightModel flightModel = conversionService.convert(flight);

        model.addAttribute("flight", flightModel);

        return "flight-search-results";
    }
}