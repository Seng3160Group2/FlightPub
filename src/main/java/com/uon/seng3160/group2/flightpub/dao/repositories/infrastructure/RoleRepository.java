package com.hossein.FlightBooking.dao.repositories.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.models.infrastructure.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
