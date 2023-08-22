package com.hossein.FlightBooking.services.impl.infrastructure;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hossein.FlightBooking.dao.repositories.infrastructure.UserRepository;
import com.hossein.FlightBooking.exceptions.ApplicationException;
import com.hossein.FlightBooking.models.infrastructure.UserModel;
import com.hossein.FlightBooking.services.infrastructure.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public long saveEntity(UserModel user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			return this.userRepo.saveAndFlush(user).getId();
		} catch (DataIntegrityViolationException exception) {
			Throwable t = exception.getCause();
		    while ((t != null) && !(t instanceof ConstraintViolationException)) {
		        t = t.getCause();
		    }			
		    
		    if (t instanceof ConstraintViolationException) {
				if (((ConstraintViolationException) t).getConstraintName().equals("uk_r43af9ap4edm43mmtq01oddj6")) {
					throw new ApplicationException("Username is Taken");
				} else if (((ConstraintViolationException) t).getConstraintName().equals("uk_6dotkott2kjsp8vw4d0m25fb7")) {
					throw new ApplicationException("Email is Taken");
				}
			}
		}
		return 0;
	}

	@Override
	public Long getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel loggedInUser = this.userRepo.findByUsername(user.getUsername())
										.orElseThrow(() -> new ApplicationException("Username Not Found"));
		return loggedInUser.getId();
	}

	@Override
	public UserModel loadById(Long id) {
		return this.userRepo.findById(id)
				.orElseThrow(() -> new ApplicationException("User Not Found"));
	}

}
