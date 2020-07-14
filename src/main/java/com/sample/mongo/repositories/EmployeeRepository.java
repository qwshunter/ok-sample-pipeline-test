package com.sample.mongo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sample.mongo.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
