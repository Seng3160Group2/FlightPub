package com.uon.seng3160.group2.flightpub.dao.repositories.companions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.dao.repositories.impl.companions.CompanionsCustomRepo;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;


public interface ICompanionsRepository extends JpaRepository<Companions, Long>, CompanionsCustomRepo {
	
	void deleteAllByBookingId(Long bookingId);
	
	List<Companions> findAllByBookingId(Long bookingId);
}
