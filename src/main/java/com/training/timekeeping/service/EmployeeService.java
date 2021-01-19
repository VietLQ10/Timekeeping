package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // get all employees
    Iterable<Employee> getAllEmployee();

    // get list employees
    List<Employee> getEmployees(String type, Object data);

    //get a employee
//    Optional<Employee> getEmployee()

    // delete all employees

    // delete list employees

    // delete a employee

    // edit a employee

    // add a employee
}
