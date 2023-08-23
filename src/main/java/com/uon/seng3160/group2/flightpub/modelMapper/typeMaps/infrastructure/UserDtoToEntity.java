package com.uon.seng3160.group2.flightpub.modelMapper.typeMaps.infrastructure;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uon.seng3160.group2.flightpub.dto.infrastructure.UserDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;


@Configuration
public class UserDtoToEntity extends GenericMapper {
	
	public UserDtoToEntity(ModelMapper mapper) {
		super(mapper);
	}

	@Bean
	public TypeMap<UserDto, UserModel> userDtoToEntityTypeMap() {
		TypeMap<UserDto, UserModel> propMap = super.mapProperties(UserDto.class, UserModel.class);
		propMap.addMapping(UserDto::getUserEmail, UserModel::setEmail);
		propMap.addMapping(UserDto::getUsername, UserModel::setUsername);
		propMap.addMapping(UserDto::getUserPass, UserModel::setPassword);
		propMap.addMapping(src -> src.getRoleId(), (dest, v) -> dest.getRole().setId((Long) v));
		propMap.addMapping(UserDto::getUserId, UserModel::setId);
		return propMap;
	}
	
}
