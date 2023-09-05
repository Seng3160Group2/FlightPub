package com.uon.seng3160.group2.flightpub.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchFormExample;
import com.uon.seng3160.group2.flightpub.service.impl.BookingServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private final BookingServiceImpl bookingServiceImpl;

    public BookingController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @GetMapping("/save-flights")
    public String saveIndex(@RequestParam("journeyIndex") int journeyIndex, Model model, HttpSession session) {
        // Store the selected list index in the session
        session.setAttribute("journeyIndex", journeyIndex);

        // Redirect to the page for displaying the selected list
        return "redirect:/bookings/ticket-select";
    }

    @GetMapping("/ticket-select")
    public String viewTickets(Model model, HttpSession session) {
        int index = (int) session.getAttribute("journeyIndex");
        List<List<FlightModel>> journeys = (List<List<FlightModel>>) session.getAttribute("flights");

        System.out.println("index: " + index);
        model.addAttribute("index", index);
        // System.out.println(journeys.get(index));
        model.addAttribute("journey", journeys.get(0).get(index));
        session.setAttribute("journey", journeys.get(0).get(index));
        return "booking-flight-info";
    }

    @GetMapping("/book-flight")
    public String bookFlight(Model model, HttpSession session, Authentication authentication) {
        List<FlightModel> journey = (List<FlightModel>) session.getAttribute("journey");
        String email = authentication.getName();

        boolean result = this.bookingServiceImpl.bookFlights(journey, email);

        return "redirect:/bookings/result?result=" + result;
    }

    @GetMapping("/result")
    public String bookingResult(@RequestParam("result") boolean result, Model model, HttpSession session) {

        model.addAttribute("result", result);
        return "booking-result";
    }
}
