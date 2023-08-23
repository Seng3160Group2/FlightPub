package com.uon.seng3160.group2.flightpub.service.impl.bookings;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.controller.viewModels.bookings.BookingsViewModel;
import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.dao.repositories.bookings.IBookingsRepository;
import com.uon.seng3160.group2.flightpub.dao.repositories.companions.ICompanionsRepository;
import com.uon.seng3160.group2.flightpub.dto.bookings.BookingsDto;
import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;
import com.uon.seng3160.group2.flightpub.exceptions.ApplicationException;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;
import com.uon.seng3160.group2.flightpub.service.bookings.IBookingsService;
import com.uon.seng3160.group2.flightpub.service.infrastructure.IUserService;


@Service
public class BookingsService implements IBookingsService {
	
	@Autowired
	private IBookingsRepository iBookingsRepository;
	
	@Autowired
	private ICompanionsRepository iCompanionsRepository;
	
	@Autowired
	private IUserService iUserService;
	

	@Override
	public List<BookingsViewModel> getAll(BookingsDto data) {
		Long userId = this.iUserService.getLoggedInUser();
		UserModel user = this.iUserService.loadById(userId);
		if (user.getRole().getRoleName().equals("ROLE_ADMIN")) {
			data.setUserId(null);
		} else if (user.getRole().getRoleName().equals("ROLE_PASSENGER")) {
			data.setUserId(userId);
		}
		return this.iBookingsRepository.getAll(data);
	}

	@Override
	public Bookings loadById(Long id) {
		Bookings booking = this.iBookingsRepository.findById(id)
				.orElseThrow(() -> new ApplicationException("Booking Not Found"));
		if (this.iUserService.getLoggedInUser() != booking.getUser().getId()) throw new ApplicationException("You Are Not Allowed To View This Booking");
		return booking;
	}

	@Override
	@Transactional
	public long saveEntity(Bookings entity) {
		Long bookingId = null;
		if (entity.getPersons() == null) entity.setPersons(0);
		
		if (entity.getId() == null) {
			
			try {
				bookingId = this.iBookingsRepository.saveAndFlush(entity).getId();	
			} catch (DataIntegrityViolationException e) {
				throw new ApplicationException("Operation Failed Due To Comprehensive Limitations");
			}
			
			Bookings booking = this.iBookingsRepository.findById(bookingId)
					.orElseThrow(() -> new ApplicationException("Booking Not Found"));
			
			for (int i = 0; i < entity.getPersons(); i++) {
				Companions companion = new Companions();
				companion.setBooking(booking);
				this.iCompanionsRepository.save(companion);
			}
			
		} else {
			
			Long companionCount = this.iCompanionsRepository.getCountByBookingId(entity.getId());
			
			Long netPersons = entity.getPersons() - companionCount;
			if (netPersons < 0) throw new ApplicationException("You Can Not Lower The Number Of Companions Without Deleting One");
			
			try {
				bookingId = this.iBookingsRepository.saveAndFlush(entity).getId();
			} catch (DataIntegrityViolationException e) {
				throw new ApplicationException("Operation Failed Due To Comprehensive Limitations");
			}
			
			if (netPersons > 0) {
				Bookings booking = this.iBookingsRepository.findById(bookingId)
						.orElseThrow(() -> new ApplicationException("Booking Not Found"));	
				
				for (int i = 0; i < netPersons; i++) {
					Companions companion = new Companions();
					companion.setBooking(booking);
					this.iCompanionsRepository.save(companion);
				}
			}
			
		}
		
		return bookingId;
	}

	@Override
	@Transactional
	public Boolean deleteEntity(Long id) {
		if (id != null) {
			Bookings booking = this.iBookingsRepository.findById(id)
					.orElseThrow(() -> new ApplicationException("Booking Can Not Be Found"));
			if (this.iUserService.getLoggedInUser() != booking.getUser().getId()) throw new ApplicationException("You Are Not Allowed To Delete This Booking");
			this.iBookingsRepository.deleteById(id);
			Optional<Bookings> entity = this.iBookingsRepository.findById(id);
			if (entity.isPresent()) return false;
			return true;
		} else {
			throw new ApplicationException("Id Can Not Be Empty");
		}
	}

	@Override
	public List<CompanionsViewModel> getAllCompanions(CompanionsDto data) {
		if (data.getBookingId() == null) throw new ApplicationException("You Have To First Choose A Booking");
		Long userId = this.iUserService.getLoggedInUser();
		UserModel user = this.iUserService.loadById(userId);
		if (user.getRole().getRoleName().equals("ROLE_ADMIN")) {
			data.setUserId(null);
		} else if (user.getRole().getRoleName().equals("ROLE_PASSENGER")) {
			data.setUserId(userId);
		}
		return this.iCompanionsRepository.getAll(data);
	}

	@Override
	public CompanionsViewModel loadCompanion(Long bookingId, Long id) {
		if (id == null) throw new ApplicationException("Id Can Not Be Empty");
		
		Long userId = this.iUserService.getLoggedInUser();
		return this.iCompanionsRepository.loadByCompanionIdAndUserId(id, bookingId, userId);
	}

	@Override
	@Transactional
	public long saveCompanion(Companions entity) {
		if (entity.getId() == null) {
			Bookings booking = this.iBookingsRepository.findById(entity.getBooking().getId())
					.orElseThrow(() -> new ApplicationException("Booking Not Found"));
			booking.setPersons(booking.getPersons() + 1);
			this.iBookingsRepository.save(booking);
		}
		
		return this.iCompanionsRepository.save(entity).getId();
	}

	@Override
	@Transactional
	public List<Companions> saveAllCompanions(List<Companions> entityList) {		
		List<Companions> newCompanions = entityList.stream().filter(e -> e.getId() == null)
											.collect(Collectors.toList());
		if (newCompanions.size() > 0) {
			Bookings booking = this.iBookingsRepository.findById(entityList.get(0).getBooking().getId())
					.orElseThrow(() -> new ApplicationException("Booking Not Found"));
			booking.setPersons(booking.getPersons() + newCompanions.size());
			this.iBookingsRepository.save(booking);
		}
		
		return this.iCompanionsRepository.saveAll(entityList);
	}

	@Override
	@Transactional
	public Boolean deleteCompanion(Long bookingId, Long id) {
		
		Long userId = this.iUserService.getLoggedInUser();
		CompanionsViewModel viewModel = this.iCompanionsRepository.loadByCompanionIdAndUserId(id, bookingId, userId);
		
		if (viewModel == null) throw new ApplicationException("You Are Not Allowed To Delete This Companion");
		
		this.iCompanionsRepository.deleteById(id);
		
		Optional<Companions> companion = this.iCompanionsRepository.findById(id);
		if (companion.isPresent()) return false;
		
		Bookings booking = this.iBookingsRepository.findById(bookingId)
				.orElseThrow(() -> new ApplicationException("Booking Not Found"));
		booking.setPersons(booking.getPersons() - 1);
		this.saveEntity(booking);
		
		return true;
	}

	@Override
	@Transactional
	public Boolean deleteAllCompanions(Long bookingId) {
		
		Long userId = this.iUserService.getLoggedInUser();
		Bookings booking = this.iBookingsRepository.findById(bookingId)
				.orElseThrow(() -> new ApplicationException("Booking Not Found"));
		
		if (userId != booking.getUser().getId()) throw new ApplicationException("You Are Not Allowed To Delete These Companions");
		
		this.iCompanionsRepository.deleteAllByBookingId(bookingId);
		
		List<Companions> companions = this.iCompanionsRepository.findAllByBookingId(bookingId);
		if (companions.size() > 0) return false;
		
		booking.setPersons(0);
		this.saveEntity(booking);
		
		return true;
	}

}
