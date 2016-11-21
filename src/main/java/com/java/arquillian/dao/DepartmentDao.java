package com.java.arquillian.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.java.arquillian.entity.DepartmentEntity;

@RequestScoped
public class DepartmentDao {
	
	@PersistenceContext(name = "testing-h2")
	private EntityManager em;

	// TODO: check if should we return object or void for insertion case in RESTFul
	/*public void insert(DepartmentEntity department) {
		em.persist(department);
	}*/
	
	public DepartmentEntity insert(DepartmentEntity department) {
		return em.merge(department);
	}

	public DepartmentEntity update(DepartmentEntity department) {
		return em.merge(department);
	}

	public DepartmentEntity find(Long id) {
		return em.find(DepartmentEntity.class, id);
	}
	
}
