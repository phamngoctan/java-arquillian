package com.java.arquillian.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.java.arquillian.dao.DepartmentDao;
import com.java.arquillian.dao.EmployeeDao;
import com.java.arquillian.entity.DepartmentEntity;
import com.java.arquillian.entity.EmployeeEntity;
import com.java.arquillian.model.Employee;

@RequestScoped
public class EmployeeService {

	@Inject
	private EmployeeDao employeeDao;
	
	@Inject
	private DepartmentDao departmentDao;
	
	public Employee add(Employee empl) {
		DepartmentEntity department = new DepartmentEntity("IT");
		DepartmentEntity departmentEntity = departmentDao.insert(department);
		
		EmployeeEntity employee = new EmployeeEntity("Tan Pham", 25, departmentEntity);
		employeeDao.insert(employee);
		
		return new Employee(employee.getId(), employee.getName(), employee.getAge(), employee.getDepartment());
	}
	
}
