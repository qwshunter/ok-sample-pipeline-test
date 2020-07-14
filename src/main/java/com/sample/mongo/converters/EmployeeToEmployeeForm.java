package com.sample.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sample.mongo.commands.EmployeeForm;
import com.sample.mongo.domain.Employee;

@Component
public class EmployeeToEmployeeForm implements Converter<Employee, EmployeeForm> {
    @Override
    public EmployeeForm convert(Employee employee) {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(employee.getId().toHexString());
        employeeForm.setName(employee.getName());
        employeeForm.setDepartment(employee.getDepartment());
        employeeForm.setRole(employee.getRole());
        return employeeForm;
    }
}
