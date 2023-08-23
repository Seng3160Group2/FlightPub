package com.uon.seng3160.group2.flightpub.dao.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository {
	
	@PersistenceContexts
	private EntityManager em;

	public Session getSession() {
		return this.em.unwrap(Session.class);
	}
	
	public <T> List<T> getAll(String hql, Map<String, Object> params) {
		return this.getAll(hql, params, null);
	}
	
	public <T> T find(String hql, Map<String, Object> params) {
		return this.find(hql, params, null);
	}
	
	public <T> List<T> getAll(String hql, Map<String, Object> params, Class<T> transformerClass) {
		Session session = this.getSession();
		Query<T> query;
		
		if (transformerClass != null) {
			query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(transformerClass));
		} else {
			query = session.createQuery(hql);
		}
		
		Set<String> parameterSet = params.keySet();
		for (Iterator<String> it = parameterSet.iterator(); it.hasNext();) {
			String parameter = it.next();
			query.setParameter(parameter, params.get(parameter));
		}
		
		return query.list();
	}
	
	public <T> T find(String hql, Map<String, Object> params, Class<T> transformerClass) {
		Session session = this.getSession();
		Query<T> query;
		
		if (transformerClass != null) {
			query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(transformerClass));
		} else {
			query = session.createQuery(hql);
		}
		
		Set<String> parameterSet = params.keySet();
		for (Iterator<String> it = parameterSet.iterator(); it.hasNext();) {
			String parameter = it.next();
			query.setParameter(parameter, params.get(parameter));
		}
		
		return query.getSingleResult();
	}
	
	public Long getRowCount(String hql, Map<String, Object> params) {
		Session session = this.getSession();
		Query<?> query;
		
		query = session.createQuery(hql);

		Set<String> parameterSet = params.keySet();
		for (Iterator<String> it = parameterSet.iterator(); it.hasNext();) {
			String parameter = it.next();
			query.setParameter(parameter, params.get(parameter));
		}
		
		return (Long) query.getSingleResult();
	}
	
}
