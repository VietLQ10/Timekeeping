package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.model.dto.EmployeeDTO;
import com.training.timekeeping.utils.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserDetailsService {

    /**
    *  create
    * */
    // create admin
    void createAdmin();

    // create user
    boolean createUser(String email, Employee employee);


    /**
    *  retrieve
    *
    * */
    // get all employees
    List<EmployeeDTO> getAllEmployee();

    // get list employees
    List<EmployeeDTO> getEmployeesDTO(String type, Object data);

    List<Employee> getEmployees(String type, Object data);

    // get a employee
    EmployeeDTO getEmployeeDTO(String type, Object data);

    Employee getEmployee(String type, Object data);

    /**
    * update
    *
    * */
    // update employee
    boolean updateEmployee(String email, Employee emp);

    /**
    * delete
    *
    *  */
    // delete list employees
    boolean deleteEmployees(String email, Iterable<Employee> employees);

    // delete a employee
    boolean deleteEmployee(String email, Employee employee);
}
