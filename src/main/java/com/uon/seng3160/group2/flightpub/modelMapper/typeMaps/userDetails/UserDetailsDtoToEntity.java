package com.hossein.FlightBooking.modelMapper.typeMaps.userDetails;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hossein.FlightBooking.dto.userDetails.UserDetailsDto;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;

@Configuration
public class UserDetailsDtoToEntity extends GenericMapper {

	public UserDetailsDtoToEntity(ModelMapper mapper) {
		super(mapper);
	}
	
	@Bean
	public TypeMap<UserDetailsDto, UserDetailsModel> dtoToDetailEntity() {
		TypeMap<UserDetailsDto, UserDetailsModel> propMap = super.mapProperties(UserDetailsDto.class, UserDetailsModel.class);
		propMap.addMapping(UserDetailsDto::getId, UserDetailsModel::setId);
		propMap.addMapping(src -> src.getUserId(), (dest, v) -> dest.getUser().setId((Long) v));
		propMap.addMapping(UserDetailsDto::getUserFName, UserDetailsModel::setFirstName);
		propMap.addMapping(UserDetailsDto::getUserLName, UserDetailsModel::setLastName);
		propMap.addMapping(UserDetailsDto::getUserCity, UserDetailsModel::setCity);
		propMap.addMapping(UserDetailsDto::getUserCountry, UserDetailsModel::setCountry);
		propMap.addMapping(UserDetailsDto::getUserPhone, UserDetailsModel::setPhone);
		propMap.addMapping(UserDetailsDto::getUserAge, UserDetailsModel::setAge);
		return propMap;
	}

}
