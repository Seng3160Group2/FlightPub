package com.uon.seng3160.group2.flightpub.controller.bookings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uon.seng3160.group2.flightpub.controller.viewModels.bookings.BookingsViewModel;
import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;
import com.uon.seng3160.group2.flightpub.exceptions.ApplicationException;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.bookings.DBookingsMapper;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.bookings.VBookingsMapper;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.companions.DCompanionsMapper;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.companions.VCompanionsMapper;
import com.uon.seng3160.group2.flightpub.service.bookings.IBookingsService;
import com.uon.seng3160.group2.flightpub.service.infrastructure.IUserService;
import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;



@RestController
@RequestMapping("/flightBooking/bookings")
public class BookingsController {

	@Autowired
	private IBookingsService iBookingsService;
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private VBookingsMapper vMapper;
	
	@Autowired
	private DBookingsMapper dMapper;
	
	@Autowired
	private DCompanionsMapper dCompanionMapper;
	
	@Autowired
	private VCompanionsMapper vCompanionMapper;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER')")
	@PostMapping("/list/grid")
	public List<BookingsViewModel> getAll(@RequestBody BookingsDto data) {
		return iBookingsService.getAll(data);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER')")
	@GetMapping("/load/{id}")
	public BookingsViewModel load(@PathVariable Long id) {
		return this.vMapper.map(this.iBookingsService.loadById(id));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER')")
	@PostMapping("/save")
	public long save(@RequestBody BookingsDto data) {
		data.setUserId(this.iUserService.getLoggedInUser());
		return this.iBookingsService.saveEntity(this.dMapper.map(data));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PASSENGER')")
	@DeleteMapping("/delete/{id}")
	public Boolean delete(@PathVariable Long id) {
		return this.iBookingsService.deleteEntity(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@PostMapping("/companions/list/grid")
	public List<CompanionsViewModel> getAllCompanions(@RequestBody CompanionsDto data) {
		return this.iBookingsService.getAllCompanions(data);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@GetMapping("/companions/load/{bookingId}/{id}")
	public CompanionsViewModel loadCompanion(@PathVariable Long bookingId, @PathVariable Long id) {
		if (bookingId == null || id == null) throw new ApplicationException("You Have To First Choose A Booking And A Companion");
		return this.iBookingsService.loadCompanion(bookingId, id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@PostMapping("/companions/save")
	public long saveCompanion(@RequestBody CompanionsDto data) {
		if (data.getBookingId() == null) throw new ApplicationException("You Have To First Choose A Booking");
		return this.iBookingsService.saveCompanion(this.dCompanionMapper.map(data));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@PostMapping("/companions/saveAll")
	public List<CompanionsViewModel> saveAll(@RequestBody List<CompanionsDto> dataList) {
		if (dataList.get(0).getBookingId() == null) throw new ApplicationException("You Have To First Choose A Booking");
		return this.vCompanionMapper.mapList(this.iBookingsService
				.saveAllCompanions(this.dCompanionMapper.mapList(dataList)));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@DeleteMapping("/companions/delete/{bookingId}/{id}")
	public Boolean deleteCompanion(@PathVariable Long bookingId, @PathVariable Long id) {
		if (bookingId == null || id == null) throw new ApplicationException("You Have To First Choose A Booking And A Companion");
		return this.iBookingsService.deleteCompanion(bookingId, id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PASSENGER')")
	@DeleteMapping("/companions/deleteAll/{bookingId}")
	public Boolean deleteAllCompanions(@PathVariable Long bookingId) {
		if (bookingId == null) throw new ApplicationException("You Have To First Choose A Booking");
		return this.iBookingsService.deleteAllCompanions(bookingId);
	}
	
}
