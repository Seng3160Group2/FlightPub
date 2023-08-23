package com.uon.seng3160.group2.flightpub.modelMapper.maps.companions;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;
import com.uon.seng3160.group2.flightpub.modelMapper.config.GenericMapper;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;


@Component
public class DCompanionsMapper extends GenericMapper {

	public DCompanionsMapper(ModelMapper mapper) {
		super(mapper);
	}
	
	public Companions map(CompanionsDto data) {
		return super.map(data, Companions.class);
	}

	public List<Companions> mapList(List<CompanionsDto> dataList) {
		return super.mapList(dataList, Companions.class);
	}
	
}
