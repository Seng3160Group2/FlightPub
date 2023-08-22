package com.hossein.FlightBooking.dao.repositories.companions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.dao.repositories.impl.companions.CompanionsCustomRepo;
import com.hossein.FlightBooking.models.companions.Companions;

public interface ICompanionsRepository extends JpaRepository<Companions, Long>, CompanionsCustomRepo {
	
	void deleteAllByBookingId(Long bookingId);
	
	List<Companions> findAllByBookingId(Long bookingId);
}
