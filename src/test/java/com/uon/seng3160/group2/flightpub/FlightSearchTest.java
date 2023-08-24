package com.uon.seng3160.group2.flightpub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.Country;
import com.uon.seng3160.group2.flightpub.model.FlightModel;
import com.uon.seng3160.group2.flightpub.model.form.FlightSearchForm;
import com.uon.seng3160.group2.flightpub.repository.DestinationRepository;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;
import com.uon.seng3160.group2.flightpub.service.BasicSearch;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;
import com.uon.seng3160.group2.flightpub.service.FlightSearchServiceImpl;

import jakarta.transaction.Transactional;

@SpringBootTest
public class FlightSearchTest {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    public FlightSearchService flightSearchService;

    @Test
    @Transactional
    public void testDestinationControllers() {

        List<List<Flight>> result = flightSearchService.getFlight("SYD", null, null, null, false);
        if (result.isEmpty())
            return;

        List<Flight> flights = result.get(0);
        System.out.println("Results:" + result);
        System.out.println("Flights at index 0:" + flights);
        
    }
}
