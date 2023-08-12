package com.uon.seng3160.group2.flightpub.repository;

import com.uon.seng3160.group2.flightpub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}