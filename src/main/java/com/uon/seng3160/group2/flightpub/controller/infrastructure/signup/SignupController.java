package com.hossein.FlightBooking.controllers.infrastructure.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hossein.FlightBooking.dto.infrastructure.UserDto;
import com.hossein.FlightBooking.modelMapper.maps.infrastructure.UserMapper;
import com.hossein.FlightBooking.services.infrastructure.IUserService;

@RestController
@RequestMapping("/flightBooking")
public class SignupController {
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private UserMapper mapper;
	

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto) throws Exception {
		userDto.setRoleId(2L);
		Long userId = this.iUserService.saveEntity(this.mapper.map(userDto));
		return ResponseEntity.ok(userId);
	}
	
}
