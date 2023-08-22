package com.hossein.FlightBooking.modelMapper.maps.companions;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hossein.FlightBooking.dto.companions.CompanionsDto;
import com.hossein.FlightBooking.modelMapper.config.GenericMapper;
import com.hossein.FlightBooking.models.companions.Companions;

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
