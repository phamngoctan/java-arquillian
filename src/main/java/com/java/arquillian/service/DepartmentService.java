package com.java.arquillian.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.java.arquillian.dao.DepartmentDao;
import com.java.arquillian.entity.DepartmentEntity;
import com.java.arquillian.model.Department;

@RequestScoped
public class DepartmentService {
	
	@Inject
	private DepartmentDao departmentDao;
	
	public Department add(Department department) {
		DepartmentEntity departmentEntity = new DepartmentEntity(department.getName());
		departmentEntity = departmentDao.insert(departmentEntity);
		department = new Department(departmentEntity.getId(), departmentEntity.getName());
		return department;
	}
	
}
