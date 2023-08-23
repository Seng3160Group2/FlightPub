package com.uon.seng3160.group2.flightpub.dao.repositories.impl.flights;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.uon.seng3160.group2.flightpub.controller.viewModels.flights.FlightsViewModel;
import com.uon.seng3160.group2.flightpub.dao.config.GenericRepository;
import com.uon.seng3160.group2.flightpub.dto.flights.FlightsDto;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;


@Repository
public class FlightsCustomRepoImpl extends GenericRepository implements FlightsCustomRepo {

	@Override
	public List<FlightsViewModel> getAll(FlightsDto data) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select"
				+ " e.id as id, e.originCountry as originCountry,"
				+ " e.originCity as originCity, e.destinationCountry as destinationCountry,"
				+ " e.destinationCity as destinationCity, e.flightNumber as flightNumber,"
				+ " e.flightDate as flightDate, e.flightTime as flightTime,"
				+ " e.price as price"
				+ " from ").append(Flights.class.getName())
				.append(" e where 1=1");
		
		if (data.getId() != null && data.getId() > -1) {
			hql.append(" and e.id = :id");
			params.put("id", data.getId());
		}
		
		if (StringUtils.hasText(data.getoCountry())) {
			hql.append(" and e.originCountry like :oCountry");
			params.put("oCountry", "%"+data.getoCountry()+"%");
		}
		
		if (StringUtils.hasText(data.getoCity())) {
			hql.append(" and e.originCity like :oCity");
			params.put("oCity", "%"+data.getoCity()+"%");
		}
		
		if (StringUtils.hasText(data.getdCountry())) {
			hql.append(" and e.destinationCountry like :dCountry");
			params.put("dCountry", "%"+data.getdCountry()+"%");
		}
		
		if (StringUtils.hasText(data.getdCity())) {
			hql.append(" and e.destinationCity like :dCity");
			params.put("dCity", "%"+data.getdCity()+"%");
		}
		
		if (data.getFlightNum() != null && data.getFlightNum() > -1) {
			hql.append(" and e.flightNumber = :flightNumber");
			params.put("flightNumber", data.getFlightNum());
		}
		
		if (StringUtils.hasText(data.getFlightDate())) {
			hql.append(" and e.flightDate like :flightDate");
			params.put("flightDate", "%"+data.getFlightDate()+"%");
		}
		
		if (StringUtils.hasText(data.getFlightTime())) {
			hql.append(" and e.flightTime like :flightTime");
			params.put("flightTime", "%"+data.getFlightTime()+"%");
		}
		
		if (data.getPrice() != null && data.getPrice() > -1) {
			hql.append(" and e.price = :price");
			params.put("price", data.getPrice());
		}
		
		return super.getAll(hql.toString(), params, FlightsViewModel.class);
	}
	
	
}
