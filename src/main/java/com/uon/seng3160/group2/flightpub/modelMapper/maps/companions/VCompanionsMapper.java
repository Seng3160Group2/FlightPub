package com.uon.seng3160.group2.flightpub.modelMapper.maps.companions;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;


@Component
public class VCompanionsMapper extends GenericMapper {

	public VCompanionsMapper(ModelMapper mapper) {
		super(mapper);
	}

	public List<CompanionsViewModel> mapList(List<Companions> entityList) {
		return super.mapList(entityList, CompanionsViewModel.class);
	}
	
}
