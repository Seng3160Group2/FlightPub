package com.uon.seng3160.group2.flightpub.repository;

import com.uon.seng3160.group2.flightpub.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

}