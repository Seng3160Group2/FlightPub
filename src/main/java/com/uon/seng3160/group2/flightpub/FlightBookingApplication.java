package com.uon.seng3160.group2.flightpub;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uon.seng3160.group2.flightpub.dao.repositories.flights.IFlightsRepository;
import com.uon.seng3160.group2.flightpub.dao.repositories.infrastructure.RoleRepository;
import com.uon.seng3160.group2.flightpub.dao.repositories.infrastructure.UserRepository;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;
import com.uon.seng3160.group2.flightpub.models.infrastructure.Roles;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;


@SpringBootApplication
public class FlightBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightBookingApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(UserRepository userRepo, RoleRepository roleRepo, IFlightsRepository iFlightsRepository) {
		
		return args -> {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			Roles adminRole = new Roles("ROLE_ADMIN");
			Roles passengerRole = new Roles("ROLE_PASSENGER");
			
			roleRepo.saveAll(List.of(adminRole, passengerRole));
			
			UserModel adminUser = new UserModel("admin@gmail.com", "admin", passwordEncoder.encode("123456"), adminRole);
			UserModel passengerUser1 = new UserModel("johndoe@gmail.com", "john_doe", passwordEncoder.encode("abcd1234"), passengerRole);
			UserModel passengerUser2 = new UserModel("janedoe@gmail.com", "jane_doe", passwordEncoder.encode("abcd1234"), passengerRole);
			
			userRepo.saveAll(List.of(adminUser, passengerUser1, passengerUser2));
			
			Flights flight1 = new Flights("usa", "texas", "england", "london", 345, "05/17/2023", "10:00 PM", 300);
			Flights flight2 = new Flights("brazil", "rio de janeiro", "usa", "ohio", 456, "05/17/2023", "03:00 PM", 400);
			Flights flight3 = new Flights("germany", "berlin", "england", "london", 567, "05/17/2023", "01:00 PM", 500);
			Flights flight4 = new Flights("usa", "ohio", "norway", "oslo", 678, "05/17/2023", "08:00 AM", 600);
			Flights flight5 = new Flights("argentina", "buenos aires", "armenia", "yerevan", 789, "05/18/2023", "10:00 PM", 700);
			Flights flight6 = new Flights("australia", "canberra", "austria", "vienna", 898, "05/19/2023", "11:00 AM", 800);
			Flights flight7 = new Flights("belgium", "brussels", "brazil", "brasilia", 987, "05/19/2023", "09:00 PM", 900);
			Flights flight8 = new Flights("chile", "santiago", "china", "beijing", 876, "05/21/2023", "10:00 AM", 1000);
			Flights flight9 = new Flights("china", "beijing", "croatia", "zagreb", 765, "05/21/2023", "01:00 AM", 1100);
			Flights flight10 = new Flights("denmark", "copenhagen", "norway", "oslo", 654, "05/25/2023", "05:00 PM", 1200);
			
			iFlightsRepository.saveAll(List.of(flight1,flight2,flight3,flight4,flight5,flight6,flight7,flight8,flight9,flight10));
			
		};
	}

}
