package com.hossein.FlightBooking.dao.repositories.impl.userDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.hossein.FlightBooking.controllers.viewModels.userDetails.UserDetailsViewModel;
import com.hossein.FlightBooking.dao.config.GenericRepository;
import com.hossein.FlightBooking.dto.userDetails.UserDetailsDto;
import com.hossein.FlightBooking.models.userDetails.UserDetailsModel;

@Repository
public class UserDetailsCustomRepoImpl extends GenericRepository implements UserDetailsCustomRepo {

	@Override
	public List<UserDetailsViewModel> getAll(UserDetailsDto data) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		hql.append("select"
				+ " e.id as id, e.user.id as userId,"
				+ " e.firstName as firstName, e.lastName as lastName,"
				+ " e.city as city, e.country as country,"
				+ " e.phone as phone, e.age as age"
				+ " from ").append(UserDetailsModel.class.getName())
				.append(" e where 1=1");
		
		if (data.getId() != null && data.getId() > -1) {
			hql.append(" and e.id = :id");
			params.put("id", data.getId());
		}
		
		if (data.getUserId() != null && data.getUserId() > -1) {
			hql.append(" and e.user.id = :userId");
			params.put("userId", data.getUserId());
		}
		
		if (StringUtils.hasText(data.getUserFName())) {
			hql.append(" and e.firstName like :firstName");
			params.put("firstName", "%"+data.getUserFName()+"%");
		}
		
		if (StringUtils.hasText(data.getUserLName())) {
			hql.append(" and e.lastName like :lastName");
			params.put("lastName", "%"+data.getUserLName()+"%");
		}
		
		if (StringUtils.hasText(data.getUserCity())) {
			hql.append(" and e.city like :city");
			params.put("city", "%"+data.getUserCity()+"%");
		}
		
		if (StringUtils.hasText(data.getUserCountry())) {
			hql.append(" and e.country like :country");
			params.put("country", "%"+data.getUserCountry()+"%");
		}
		
		if (StringUtils.hasText(data.getUserPhone())) {
			hql.append(" and e.phone like :phone");
			params.put("phone", "%"+data.getUserPhone()+"%");
		}
		
		if (data.getUserAge() != null && data.getUserAge() > -1) {
			hql.append(" and e.age = :age");
			params.put("age", data.getUserAge());
		}
		
		return super.getAll(hql.toString(), params, UserDetailsViewModel.class);
	}

}
