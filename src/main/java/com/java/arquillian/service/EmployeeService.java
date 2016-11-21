package com.java.arquillian.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.java.arquillian.dao.EmployeeDao;
import com.java.arquillian.entity.DepartmentEntity;
import com.java.arquillian.entity.EmployeeEntity;
import com.java.arquillian.model.Department;
import com.java.arquillian.model.Employee;

@RequestScoped
@Transactional
public class EmployeeService {

	@Inject
	private EmployeeDao employeeDao;
	
	public Employee add(Employee empl) {
//		DepartmentEntity department = new DepartmentEntity(empl.getDepartment().getId(), empl.getDepartment().getName());
//		DepartmentEntity departmentEntity = departmentDao.insert(department);
		
		DepartmentEntity department = new DepartmentEntity(empl.getDepartment().getId(), empl.getDepartment().getName());
		EmployeeEntity insertedEmployee = new EmployeeEntity(empl.getName(), empl.getAge(), department);

		employeeDao.insert(insertedEmployee);
		return new Employee(insertedEmployee.getId(), insertedEmployee.getName(), insertedEmployee.getAge(),
				new Department(insertedEmployee.getDepartment().getId(), insertedEmployee.getDepartment().getName()));
	}
	
}
