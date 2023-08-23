package com.uon.seng3160.group2.flightpub.controller.userDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uon.seng3160.group2.flightpub.controller.viewModels.userDetails.UserDetailsViewModel;
import com.uon.seng3160.group2.flightpub.dto.userDetails.UserDetailsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.userDetails.UserDetailsMapper;
import com.uon.seng3160.group2.flightpub.service.impl.infrastructure.UserService;
import com.uon.seng3160.group2.flightpub.service.userDetails.IUserDetailsService;


@RestController
@RequestMapping("/flightBooking/userDetails")
public class UserDetailsController {
	
	@Autowired
	private IUserDetailsService iUserDetailsService;
	
	@Autowired
	private UserDetailsMapper mapper;
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/list/grid")
	public List<UserDetailsViewModel> getAll(@RequestBody UserDetailsDto dto) {
		return this.iUserDetailsService.getAll(dto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@PostMapping("/save")
	public long save(@RequestBody UserDetailsDto dto) {
		dto.setUserId(this.userService.getLoggedInUser());
		return this.iUserDetailsService.saveEntity(this.mapper.map(dto));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@GetMapping("/load")
	public UserDetailsViewModel load() {
		return this.iUserDetailsService.loadByUserId(this.userService.getLoggedInUser());
	}
	
}
