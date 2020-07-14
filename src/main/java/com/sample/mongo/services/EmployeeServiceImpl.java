package com.sample.mongo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.mongo.commands.EmployeeForm;
import com.sample.mongo.converters.EmployeeFormToEmployee;
import com.sample.mongo.domain.Employee;
import com.sample.mongo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeFormToEmployee employeeformToEmployee;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeFormToEmployee employeeformToemployee) {
		this.employeeRepository = employeeRepository;
		this.employeeformToEmployee = employeeformToemployee;
	}


    @Override
    public List<Employee> listAll() {
        List<Employee> employee = new ArrayList<>();
        employeeRepository.findAll().forEach(employee::add);
        return employee;
    }

    @Override
    public Employee getById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveOrUpdate(Employee product) {
    	employeeRepository.save(product);
        return product;
    }

    @Override
    public Employee saveOrUpdateEmployeeform(EmployeeForm employeeform) {
        Employee savedEmployee = saveOrUpdate(employeeformToEmployee.convert(employeeform));

        System.out.println("Saved Employee Id: " + savedEmployee.getId());
        return savedEmployee;
    }
}
