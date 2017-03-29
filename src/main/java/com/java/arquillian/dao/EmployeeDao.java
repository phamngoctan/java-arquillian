package com.java.arquillian.dao;

import javax.enterprise.context.RequestScoped;

import com.java.arquillian.entity.EmployeeEntity;

@RequestScoped
public class EmployeeDao extends BaseDao<EmployeeEntity> {

	/*public void insert(EmployeeEntity employee) {
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
*/
}
