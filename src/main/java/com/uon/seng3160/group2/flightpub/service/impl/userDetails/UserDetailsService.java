package com.hossein.FlightBooking.services.impl.userDetails;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hossein.FlightBooking.controllers.viewModels.userDetails.UserDetailsViewModel;
import com.hossein.FlightBooking.dao.repositories.userDetails.IUserDetailsRepository;
import com.hossein.FlightBooking.dto.userDetails.UserDetailsDto;
import com.hossein.FlightBooking.modelMapper.maps.userDetails.VUserDetailsMapper;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;
import com.hossein.FlightBooking.services.userDetails.IUserDetailsService;

@Service
public class UserDetailsService implements IUserDetailsService {
	
	@Autowired
	private IUserDetailsRepository iUserDetailsRepository;
	
	@Autowired
	private VUserDetailsMapper vMapper;

	@Override
	public List<UserDetailsViewModel> getAll(UserDetailsDto data) {
		return this.iUserDetailsRepository.getAll(data);
	}

	@Override
	@Transactional
	public long saveEntity(UserDetailsModel entity) {
		return this.iUserDetailsRepository.save(entity).getId();
	}

	@Override
	public UserDetailsViewModel loadByUserId(Long id) {
		Optional<UserDetailsModel> entity = this.iUserDetailsRepository.findByUserId(id);
		if (entity.isPresent()) return this.vMapper.map(entity.get());
		return new UserDetailsViewModel();
	}

}
