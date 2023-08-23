package com.uon.seng3160.group2.flightpub.dao.repositories.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.models.infrastructure.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
