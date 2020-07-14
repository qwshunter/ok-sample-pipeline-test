package com.sample.mongo.services;

import java.util.List;

import com.sample.mongo.commands.EmployeeForm;
import com.sample.mongo.domain.Employee;

public interface EmployeeService {

    List<Employee> listAll();

    Employee getById(String id);

    Employee saveOrUpdate(Employee employee);

    Employee saveOrUpdateEmployeeform(EmployeeForm employeeForm);
}
