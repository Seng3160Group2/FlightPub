package com.uon.seng3160.group2.flightpub.repository;

import com.uon.seng3160.group2.flightpub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}