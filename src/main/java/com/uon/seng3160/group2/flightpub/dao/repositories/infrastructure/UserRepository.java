package com.hossein.FlightBooking.dao.repositories.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.models.infrastructure.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByUsername(String username);
}
