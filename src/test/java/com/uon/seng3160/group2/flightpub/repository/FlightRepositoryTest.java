package com.uon.seng3160.group2.flightpub.repository;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.uon.seng3160.group2.flightpub.entity.Flight;

import jakarta.persistence.EntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@SpringBootTest
public class FlightRepositoryTest {

    @Autowired
    private FlightRepository userRepository;

    @Test
    @Sql(scripts = "/test-flights-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void injectedComponentsAreNotNull() {
        List<Flight> flights = userRepository.findAll();
        assertNotEquals(0, flights.size());
        System.out.println("hello!");
    }
}
