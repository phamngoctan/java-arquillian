package com.java.arquillian.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.java.arquillian.model.Department;
import com.java.arquillian.model.Employee;

@RunWith(Arquillian.class)
public class EmployeeServiceIT {
	 
    @Deployment
    public static WebArchive createDeployment() {
    	
    	WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
    	.addPackages(true, excludeTests(), "com.java.arquillian")
    	.addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml")
    	.addAsWebInfResource(EmptyAsset.INSTANCE, "classes/META-INF/beans.xml")
    	;
    	
    	// LIBS, if you use others library not included in java ee
    	/*
        MavenResolverSystem resolver = Maven.resolver();
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.primefaces:primefaces").withoutTransitivity().asSingleFile());
    	war.addAsLibraries(pomResolver.resolve("org.mockito:mockito-core").withTransitivity().asFile());
    	
    	// if you use primefaces
    	war.addAsWebInfResource(new File(WEB_INF,"web.xml"), "web.xml");
        war.addAsWebInfResource(new File(WEB_INF,"faces-config.xml"), "faces-config.xml");
    	*/
    	
    	return war;
    }
    
    private static Filter<ArchivePath> excludeTests() {
		return Filters.exclude(".*Test.class");
	}
 
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private DepartmentService departmentService;
    
    private Department department;
    
    @Before
    public void init() {
    	department = new Department("IT department");
    	Department addedDepartment = departmentService.add(department);
    	department = new Department(addedDepartment.getId(), addedDepartment.getName());
    }
 
    @Test
    public void add_happyCase() {
		Employee employee = new Employee("Tan Pham", 25, department);
		Employee actual = employeeService.add(employee);
		
		Assert.assertThat(actual, notNullValue());
		Assert.assertThat(actual.getName(), is(equalTo("Tan Pham")));
		Assert.assertThat(actual.getAge(), is(equalTo(25)));
		Assert.assertThat(actual.getDepartment(), is(equalTo(department)));
		
		department.setName("HR department");
		Employee employee2 = new Employee("Jame Bond", 30, department);
		Employee actual2 = employeeService.add(employee2);
		Assert.assertThat(actual2, notNullValue());
		Assert.assertThat(actual2.getName(), is(equalTo("Jame Bond")));
		Assert.assertThat(actual2.getAge(), is(equalTo(30)));
		Assert.assertThat(actual2.getDepartment().getName(), is(equalTo("HR department")));
    }
	
}
