package com.uon.seng3160.group2.flightpub.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.entity.Account;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.repository.AccountRepository;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;
import com.uon.seng3160.group2.flightpub.service.AccountService;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;

@Service
public class BookingServiceImpl {
    @Autowired
    Formatter<LocalDateTime> formatter;

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final FlightSearchService flightSearchService;

    @Autowired
    private final FlightRepository flightRepository;

    public BookingServiceImpl(AccountService accountService, FlightSearchService flightSearchService,
            FlightRepository flightRepository) {
        this.accountService = accountService;
        this.flightSearchService = flightSearchService;
        this.flightRepository = flightRepository;
    }

    public boolean bookFlights(List<FlightModel> flightModels, String email) {
        List<Flight> flights = new ArrayList<Flight>();
        try {
            for (FlightModel flight : flightModels) {
                System.out.println(flight);
                Optional<Flight> flightResult = this.flightSearchService.getFlight(flight.getAirlineCode(),
                        flight.getFlightNumber(),
                        formatter.parse(flight.getDepartureTime(), Locale.ENGLISH));
                if (flightResult.isPresent())
                    flights.add(flightResult.get());
            }
            Account account = this.accountService.findByEmail(email);
            System.out.println("Account");
            System.out.println(account);

            for (Flight flight : flights) {
                flight.addUser(account);
                this.flightRepository.save(flight);
            }

            // List<Flight> accountFlights = account.getFlights();

            // account.addFlights(flights);
            // this.accountRepository.save(account);

        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
