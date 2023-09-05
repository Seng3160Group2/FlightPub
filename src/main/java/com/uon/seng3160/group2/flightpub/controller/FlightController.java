package com.uon.seng3160.group2.flightpub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchFormExample;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    public FlightSearchService flightSearchService;

    @Autowired
    public Converter<Flight, FlightModel> conversionService;

    @GetMapping("/search-example")
    public String searchFormExample(Model model) {
        model.addAttribute("flightSearchForm", new FlightSearchFormExample());
        return "flight-search-example";
    }

    @GetMapping("/search-results-example")
    public String searchExample(@Valid @ModelAttribute FlightSearchFormExample flightSearchFormExample,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "flight-search-example";
        }

        Optional<Flight> result = flightSearchService.getFlight(
                flightSearchFormExample.getAirlineCode(),
                flightSearchFormExample.getFlightNumber(),
                flightSearchFormExample.getDepartureTime());

        if (result.isEmpty()) {
            model.addAttribute("flight", null);

            return "flight-search-results-exmaple";
        }
        Flight flight = result.get();
        FlightModel flightModel = conversionService.convert(flight);

        model.addAttribute("flight", flightModel);

        return "flight-search-results-example";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("flightSearchForm", new FlightSearchForm());
        return "flight-search";
    }

    @GetMapping("/search-results")
    public String departureResults(@Valid @ModelAttribute FlightSearchForm flightSearchForm,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            return "flight-search";
        }

        boolean isReturn = (flightSearchForm.getReturnTime() != null) ? true : false;

        List<List<List<Flight>>> result = flightSearchService.getFlights(flightSearchForm.getDeparture(),
                flightSearchForm.getDestination(), flightSearchForm.getDepartureTime(),
                flightSearchForm.getReturnTime(), isReturn);

        List<List<List<FlightModel>>> resultAsModel = entityToModel(result);
        System.out.println(resultAsModel);

        session.setAttribute("flights", resultAsModel);

        model.addAttribute("departures", resultAsModel.get(0));

        return "flight-search-results";
    }

    public List<List<List<FlightModel>>> entityToModel(List<List<List<Flight>>> departuresAndReturns) {
        List<FlightModel> journeyModel;
        List<List<FlightModel>> journeysModel;
        List<List<List<FlightModel>>> departuresAndReturnsModel = new ArrayList<List<List<FlightModel>>>();
        for (List<List<Flight>> journeys : departuresAndReturns) {
            journeysModel = new ArrayList<List<FlightModel>>();
            for (List<Flight> journey : journeys) {
                journeyModel = new ArrayList<FlightModel>();
                for (Flight flight : journey) {
                    journeyModel.add(conversionService.convert(flight));
                }
                journeysModel.add(journeyModel);
            }
            departuresAndReturnsModel.add(journeysModel);
        }
        return departuresAndReturnsModel;
    }
}