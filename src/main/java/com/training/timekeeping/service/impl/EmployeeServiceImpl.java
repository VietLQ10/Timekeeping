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

import java.time.LocalTime;
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
    public boolean deleteEmployees(Iterable<Employee> employees, String email) {
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(findEmployee(Constant.EMPLOYEE_EMAIL, email).getRole())) {
            repository.deleteAll(employees);
            return true;
        }
        return false;
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
    public Employee addEmployee(Employee employee) {
        employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
        return repository.save(employee);
    }

    @Override
    public void createAdmin() {
        Employee emp = new Employee();
        emp.setEmployeeId("1");
        emp.setDepartment(null);
        emp.setPosition(null);
        emp.setGender(null);
        emp.setName("La Quoc Viet");
        emp.setEmail("vietlq@ominext.com");
        emp.setPassword(bcryptEncoder.encode("vietlq@ominext.com"));
        emp.setNumDayoff(3);
        emp.setNumRemaining(2);
        emp.setTimeStartWork(LocalTime.MAX);
        emp.setTimeBreak(LocalTime.MAX);
        emp.setTimeEndWork(LocalTime.MAX);
        emp.setRole(Constant.ROLE_ADMIN);
        repository.save(emp);
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
