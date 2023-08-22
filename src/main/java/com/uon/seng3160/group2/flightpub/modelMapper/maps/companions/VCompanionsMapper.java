package com.hossein.FlightBooking.modelMapper.maps.companions;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.controllers.viewModels.companions.CompanionsViewModel;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.companions.Companions;

@Component
public class VCompanionsMapper extends GenericMapper {

	public VCompanionsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public List<CompanionsViewModel> mapList(List<Companions> entityList) {
		return super.mapList(entityList, CompanionsViewModel.class);
	}
	
}
