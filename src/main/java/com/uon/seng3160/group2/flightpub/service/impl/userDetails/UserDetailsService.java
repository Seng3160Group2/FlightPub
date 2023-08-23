package com.uon.seng3160.group2.flightpub.service.impl.userDetails;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.controller.viewModels.userDetails.UserDetailsViewModel;
import com.uon.seng3160.group2.flightpub.dao.repositories.userDetails.IUserDetailsRepository;
import com.uon.seng3160.group2.flightpub.dto.userDetails.UserDetailsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.maps.userDetails.VUserDetailsMapper;
import com.uon.seng3160.group2.flightpub.models.userDetails.UserDetailsModel;
import com.uon.seng3160.group2.flightpub.service.userDetails.IUserDetailsService;


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
