package com.sample.mongo.converters;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.sample.mongo.commands.EmployeeForm;
import com.sample.mongo.domain.Employee;


@Component
public class EmployeeFormToEmployee implements Converter<EmployeeForm, Employee> {

    @Override
    public Employee convert(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        if (employeeForm.getId() != null  && !StringUtils.isEmpty(employeeForm.getId())) {
        	employee.setId(new ObjectId(employeeForm.getId()));
        }
        employee.setName(employeeForm.getName());
        employee.setDepartment(employeeForm.getDepartment());
        employee.setRole(employeeForm.getRole());
        return employee;
    }
}
