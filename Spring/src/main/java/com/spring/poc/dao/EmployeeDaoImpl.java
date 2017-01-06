package com.spring.poc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.spring.poc.model.Employee;


@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	MongoTemplate mongoOperations;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoOperations = mongoTemplate;
	}
	public void addEmployee(Employee employee) {
		mongoOperations.save(employee);
	}
	public void deleteEmployee(Employee employee) {
		mongoOperations.remove(employee);
	}
	
	public List<Employee> listEmployeess() {
		return (List<Employee>) mongoOperations.findAll(Employee.class);
	}

	@Cacheable(value="getEmployeeCache", key="#empid")
	public Employee getEmployee(int empid) {
		
		Query query = new Query(Criteria.where("id").is(String.valueOf(empid)));
		Employee emp = (Employee) mongoOperations.findOne(query, Employee.class);
		System.out.println("fetching Employee from Database.....................");
		return emp;
		
		
		//return (Employee) mongoOperations.findById(empid, Employee.class);
	}

	

}
