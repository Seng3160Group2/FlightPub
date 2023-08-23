package com.uon.seng3160.group2.flightpub.modelMapper.typeMaps.companions;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;

@Configuration
public class EntityToViewModelCompanions extends GenericMapper {

	public EntityToViewModelCompanions(ModelMapper mapper) {
		super(mapper);
	}

	@Bean
	public TypeMap<Companions, CompanionsViewModel> eToVCompanions() {
		TypeMap<Companions, CompanionsViewModel> propMap = super.mapProperties(Companions.class, CompanionsViewModel.class);
		propMap.addMapping(Companions::getId, CompanionsViewModel::setId);
		propMap.addMapping(src -> src.getBooking().getId(), CompanionsViewModel::setBookingId);
		propMap.addMapping(Companions::getFirstName, CompanionsViewModel::setFirstName);
		propMap.addMapping(Companions::getLastName, CompanionsViewModel::setLastName);
		propMap.addMapping(Companions::getAge, CompanionsViewModel::setAge);
		return propMap;
	}
	
	
}
