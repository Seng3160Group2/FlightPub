package com.hossein.FlightBooking.modelMapper.typeMaps.userDetails;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hossein.FlightBooking.controllers.viewModels.userDetails.UserDetailsViewModel;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;

@Configuration
public class EntityToUserDetailsViewModel extends GenericMapper {

	public EntityToUserDetailsViewModel(ModelMapper mapper) {
		super(mapper);
	}

	@Bean
	public TypeMap<UserDetailsModel, UserDetailsViewModel> eToVUserDetails() {
		TypeMap<UserDetailsModel, UserDetailsViewModel> propMap = super.mapProperties(UserDetailsModel.class, UserDetailsViewModel.class);
		propMap.addMapping(UserDetailsModel::getId, UserDetailsViewModel::setId);
		propMap.addMapping(src -> src.getUser().getId(), UserDetailsViewModel::setUserId);
		propMap.addMapping(UserDetailsModel::getFirstName, UserDetailsViewModel::setFirstName);
		propMap.addMapping(UserDetailsModel::getLastName, UserDetailsViewModel::setLastName);
		propMap.addMapping(UserDetailsModel::getCity, UserDetailsViewModel::setCity);
		propMap.addMapping(UserDetailsModel::getCountry, UserDetailsViewModel::setCountry);
		propMap.addMapping(UserDetailsModel::getPhone, UserDetailsViewModel::setPhone);
		propMap.addMapping(UserDetailsModel::getAge, UserDetailsViewModel::setAge);
		return propMap;
	}
	
}
