package com.uon.seng3160.group2.flightpub.dao.repositories.userDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.dao.repositories.impl.userDetails.UserDetailsCustomRepo;
import com.uon.seng3160.group2.flightpub.models.userDetails.UserDetailsModel;

public interface IUserDetailsRepository extends JpaRepository<UserDetailsModel, Long>, UserDetailsCustomRepo {
	Optional<UserDetailsModel> findByUserId(Long id);
}
