package com.uon.seng3160.group2.flightpub.dao.repositories.infrastructure;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	Optional<UserModel> findByUsername(String username);
}
