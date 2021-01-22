package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.utils.Constant;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.repository.EmployeeRepository;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Iterable<Employee> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    public List<Employee> getEmployees(String type, Object data) {
        List<Employee> employees = new ArrayList<>();
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_NAME:
                employees = repository.findByName((String) data);
                break;

        }
        return employees;
    }

    @Override
    public Employee findEmployee(String type, Object data) {
        Optional<Employee> optional = null;
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_ID:
                optional = repository.findById((String) data);
                break;

            case Constant.EMPLOYEE_EMAIL:
                optional = repository.findByEmail((String) data);
                break;
        }

        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

//    @Override
//    public Employee findByEmail(String email) {
//        Optional<Employee> optional = repository.findByEmail(email);
//
//        if (optional.isPresent()) {
//            return optional.get();
//        }
//
//        return null;
//    }

    @Override
    public void deleteAllEmployees(Role role) {
        if (role == Role.ADMIN) {
            repository.deleteAll();
        }
    }

    @Override
    public void deleteEmployees(Iterable<Employee> employees, Role role) {
        if (role == Role.ADMIN) {
            repository.deleteAll(employees);
        }
    }

    @Override
    public void deleteEmployee(Employee employee, Role role) {
        if (role == Role.ADMIN) {
            if (employee == null) {
                return;
            }
            repository.delete(employee);
        }

    }

    @Override
    public void updateEmployee(Employee employee, Role role) {
         if (role == Role.ADMIN) {

         } else {

         }
    }

    @Override
    public void addEmployee(Employee employee, Role role) {
        if(role == Role.ADMIN) {

        }
    }

    @Override
    public Employee save(Account account) {
        Employee employee = new Employee();
        employee.setEmail(account.getEmail());
        employee.setPassword(bcryptEncoder.encode(account.getPassword()));
        return repository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> optional = repository.findByEmail(email);
        if (! optional.isPresent()) {
            throw new UsernameNotFoundException("Email not found with email: " + email);
        }
        return new User(optional.get().getEmail(), optional.get().getPassword(), new ArrayList<>());
    }
}
