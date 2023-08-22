package com.hossein.FlightBooking.dao.repositories.userDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.dao.repositories.impl.userDetails.UserDetailsCustomRepo;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;

public interface IUserDetailsRepository extends JpaRepository<UserDetailsModel, Long>, UserDetailsCustomRepo {
	Optional<UserDetailsModel> findByUserId(Long id);
}
