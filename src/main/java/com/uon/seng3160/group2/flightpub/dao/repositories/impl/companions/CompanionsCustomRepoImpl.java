package com.uon.seng3160.group2.flightpub.dao.repositories.impl.companions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.uon.seng3160.group2.flightpub.controller.viewModels.companions.CompanionsViewModel;
import com.uon.seng3160.group2.flightpub.dao.config.GenericRepository;
import com.uon.seng3160.group2.flightpub.dto.companions.CompanionsDto;
import com.uon.seng3160.group2.flightpub.models.companions.Companions;


@Repository
public class CompanionsCustomRepoImpl extends GenericRepository implements CompanionsCustomRepo {

	@Override
	public List<CompanionsViewModel> getAll(CompanionsDto data) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select"
				+ " e.id as id, e.firstName as firstName, booking.id as bookingId,"
				+ " e.lastName as lastName, e.age as age"
				+ " from ")
			.append(Companions.class.getName())
			.append(" e"
				+ " left join Bookings booking on booking.id = e.booking.id"
				+ " left join UserModel user on user.id = booking.user.id"
				+ " where 1=1");
		
		if (data.getId() != null && data.getId() > -1) {
			hql.append(" and e.id = :id");
			params.put("id", data.getId());
		}
		
		if (data.getBookingId() != null && data.getBookingId() > -1) {
			hql.append(" and booking.id = :bookingId");
			params.put("bookingId", data.getBookingId());
		}
		
		if (StringUtils.hasText(data.getfName())) {
			hql.append(" and e.firstName like :firstName");
			params.put("firstName", "%"+data.getfName()+"%");
		}
		
		if (StringUtils.hasText(data.getlName())) {
			hql.append(" and e.lastName like :lastName");
			params.put("lastName", "%"+data.getlName()+"%");
		}
		
		if (data.getAge() != null && data.getAge() > -1) {
			hql.append(" and e.age = :age");
			params.put("age", data.getAge());
		}
		
		if (data.getUserId() != null && data.getUserId() > -1) {
			hql.append(" and user.id = :userId");
			params.put("userId", data.getUserId());
		}
		
		return super.getAll(hql.toString(), params, CompanionsViewModel.class);
	}

	@Override
	public CompanionsViewModel loadByCompanionIdAndUserId(Long companionId, Long bookingId, Long userId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select"
				+ " e.id as id, e.firstName as firstName, booking.id as bookingId,"
				+ " e.lastName as lastName, e.age as age"
				+ " from ")
			.append(Companions.class.getName())
			.append(" e"
				+ " left join Bookings booking on booking.id = e.booking.id"
				+ " left join UserModel user on user.id = booking.user.id"
				+ " where 1=1");
		
		if (companionId != null && companionId > -1) {
			hql.append(" and e.id = :companionId");
			params.put("companionId", companionId);
		}
		
		if (bookingId != null && bookingId > -1) {
			hql.append(" and booking.id = :bookingId");
			params.put("bookingId", bookingId);
		}
		
		if (userId != null && userId > -1) {
			hql.append(" and user.id = :userId");
			params.put("userId", userId);
		}
		
		return super.find(hql.toString(), params, CompanionsViewModel.class);
	}

	@Override
	public Long getCountByBookingId(Long bookingId) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder hql = new StringBuilder();
		
		hql.append("select count(*)"
				+ " from ")
			.append(Companions.class.getName())
			.append(" e"
				+ " left join Bookings booking on booking.id = e.booking.id"
				+ " where 1=1");
		
		if (bookingId != null && bookingId > -1) {
			hql.append(" and booking.id = :bookingId");
			params.put("bookingId", bookingId);
		}
		
		return super.getRowCount(hql.toString(), params);
	}

}
