package com.hossein.FlightBooking.controllers.home;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flightBooking")
public class HomeController {
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@GetMapping("/user/home")
	public String getUserHome() {
		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "Hi " + loggedInUser.getUsername() + " Welcome To Flight Booking App";
	}

}
