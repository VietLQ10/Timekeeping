package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.utils.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {

    // get all employees
    Iterable<Employee> getAllEmployee();

    // get list employees
    List<Employee> getEmployees(String type, Object data);

    // get employee
    Employee findEmployee(String type, Object data);

    // delete all employees
    void deleteAllEmployees(Role role);

    // delete list employees
    void deleteEmployees(Iterable<Employee> employees, Role role);

    // delete a employee
    void deleteEmployee(Employee employee, Role role);

    // edit a employee
    void updateEmployee(Employee employee, Role role);

    // add a employee
    void addEmployee(Employee employee, Role role);

    Employee save(Account account);
}
