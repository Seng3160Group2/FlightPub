package com.hossein.FlightBooking.controllers.infrastructure.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hossein.FlightBooking.dto.infrastructure.AuthReq;
import com.hossein.FlightBooking.dto.infrastructure.AuthRes;
import com.hossein.FlightBooking.security.jwt.JwtTokenUtil;
import com.hossein.FlightBooking.services.impl.infrastructure.FlightBookingUserDetailsService;

@RestController
@RequestMapping("/flightBooking")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private FlightBookingUserDetailsService flightBookingUserDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthReq authReq) throws Exception {
		this.authenticate(authReq.getUsername(), authReq.getPassword());
		final UserDetails userDetails = flightBookingUserDetailsService.loadUserByUsername(authReq.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthRes(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_EXCEPTION", e);
		}
	}
	
}
