package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // get all employees
    Iterable<Employee> getAllEmployee();

    // get list employees
    List<Employee> getEmployees(String type, Object data);

    // delete all employees
    void deleteAllEmployees();

    // delete list employees
    void deleteEmployees(Iterable<Employee> employees);

    // delete a employee
    void deleteEmployee(Employee employee);

    // edit a employee

    // add a employee
}
