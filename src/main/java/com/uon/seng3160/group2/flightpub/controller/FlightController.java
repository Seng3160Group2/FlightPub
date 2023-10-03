package com.uon.seng3160.group2.flightpub.controller;

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

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchForm;
import com.uon.seng3160.group2.flightpub.service.BasicSearch;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;

import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("flightSearchForm", new FlightSearchForm());
        return "flight-search";
        
    }

    @GetMapping("/search-results")
    public String getFlightById(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "flight-search";
        }

        List<List<Flight>> result = flightSearchService.getFlight(
                flightSearchForm.getStartDestination(),
                flightSearchForm.getEndDestination(),
                flightSearchForm.getDepartureTime(),
                flightSearchForm.getArrivalTime(),
                false);

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
        session.setAttribute("selectedDepartureFlight", result);
        return "flight-search-results";
    }
    @GetMapping("/flight-search-returns")
    public String returnFlight(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult, Model model, HttpSession session){
            
            List<FlightModel> returnFlightOptions = flightSearchService.getReturnFlight(session.getAttribute("selectedDepartureFlight"), true);

            model.addAttribute("selectedDepartureFlight", getReturnFlight(session.getAttribute("selectedDepartureFlight")));
            model.addAttribute("returnFlightOptions", returnFlightOptions);


            return "/flight-search-returns";
            }
}