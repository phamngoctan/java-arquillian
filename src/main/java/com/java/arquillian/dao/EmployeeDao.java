package com.java.arquillian.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.java.arquillian.entity.EmployeeEntity;

@RequestScoped
public class EmployeeDao {

	@PersistenceContext(name = "testing-h2")
	private EntityManager em;

	public void insert(EmployeeEntity employee) {
		em.persist(employee);
	}

	public void update(EmployeeEntity employee) {
		em.merge(employee);
	}
	
	public EmployeeEntity find(Long id) {
		return em.find(EmployeeEntity.class, id);
	}

	public List<EmployeeEntity> findAll() {
		return em.createQuery("SELECT p FROM Person p ORDER BY p.id", EmployeeEntity.class).getResultList();
	}

}
