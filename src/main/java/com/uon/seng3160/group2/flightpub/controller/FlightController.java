package com.uon.seng3160.group2.flightpub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchForm;
import com.uon.seng3160.group2.flightpub.service.AccountService;
import com.uon.seng3160.group2.flightpub.service.BasicSearch;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    public FlightSearchService flightSearchService;

    //@Autowired
    //public BasicSearch basicSearch;

    @Autowired
    public Converter<Flight, FlightModel> conversionService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/search")
    public String showSearchForm(Model model, Principal principal) {
        String userFirstName = ""; // Initialize with an empty string
        String userLastName = ""; // Initialize with an empty string
        String userEmail = ""; // Initialize with an empty string

        // Check if a user is logged in
        if (principal != null) {
            // Get the currently logged-in user's username (typically email or username)
            String username = principal.getName();

            // Use your account service to fetch user details by username or email
            // Replace "accountService.findByEmail(username)" with your actual method
            Account userAccount = accountService.findByEmail(username);

            // Check if the user account was found
            if (userAccount != null) {
                // Retrieve the user's first name from the account object
                userFirstName = userAccount.getFirstName();
                userLastName = userAccount.getLastName();
                userEmail = userAccount.getEmail();
            }
        }

        // Add the user's first name to the model
        model.addAttribute("userFirstName", userFirstName);
        model.addAttribute("userLastName", userLastName);
        model.addAttribute("userEmail", userEmail);

        // Add the flight search form to the model as well
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

        //Optional<Flight> result = 
        List<List<Flight>> result = flightSearchService.getFlight(
                flightSearchForm.getStartDestination(),
                flightSearchForm.getEndDestination(),
                flightSearchForm.getDepartureTime(),
                flightSearchForm.getArrivalTime(),
                false);

        //flightSearchForm.getDepartureDestination();
        //flightSearchForm.getStopOverDestination();
                
        
        if (result.isEmpty()) {
            model.addAttribute("flight", null);

            return "flight-search-results";
        }

        List<List<FlightModel>> flightModels = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            List<Flight> flightList = result.get(i);
            List<FlightModel> journey = new ArrayList<FlightModel>();
            for (int j = 0; j < flightList.size(); j++) {
                Flight flight = flightList.get(j);
                FlightModel flightModel = conversionService.convert(flight);
                journey.add(flightModel);
                
            }
            flightModels.add(journey);
        } 
        
        model.addAttribute("journeys", flightModels);
        //Flight flight = result.get(1);
        //FlightModel flightModel = conversionService.convert(flight);
        return "flight-search-results";
    }
    
    @GetMapping("/flight-search-returns")
    public String getReturnFlight(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult, Model model){
            return "/flight-search-returns";
            }
}