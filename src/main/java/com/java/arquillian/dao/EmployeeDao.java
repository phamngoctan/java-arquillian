package com.java.arquillian.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.java.arquillian.entity.DepartmentEntity;
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
	
	public List<EmployeeEntity> findByName(String name) {
		final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery cq = cb.createQuery();
		final Root holderEntity = cq.from(getPersistentClass());
		Join<Root<EmployeeEntity>, DepartmentEntity> contractJoin = holderEntity.join("department");
		cq.select(holderEntity);
		List<Predicate> where = new ArrayList<>();
		
		if (name != null) {
			where.add(cb.equal(holderEntity.get("name"), name));
		}
		
		if (where.size() > 0) {
			cq.where(where.toArray(new Predicate[where.size()]));
		}
		
		return (List<EmployeeEntity>) getEntityManager().createQuery(cq).getResultList();
	}
	
}
