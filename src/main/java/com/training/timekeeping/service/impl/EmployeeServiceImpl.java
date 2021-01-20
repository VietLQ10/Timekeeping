package com.training.timekeeping.service.impl;

import com.training.timekeeping.Utils.Constant;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.repository.EmployeeRepository;
import com.training.timekeeping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Iterable<Employee> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    public List<Employee> getEmployees(String type, Object data) {
        List<Employee> employees = new ArrayList<>();
        Optional<Employee> optional;

        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_ID:
                optional = repository.findById((String) data);
                if (optional.isPresent()) {
                    employees.add(optional.get());
                }
                break;

            case Constant.EMPLOYEE_EMAIL:
                optional = repository.findByEmail((String) data);
                if (optional.isPresent())  {
                    employees.add(optional.get());
                }
                break;

            case Constant.EMPLOYEE_NAME:
                employees = repository.findByName((String) data);
                break;

        }
        return employees;
    }

    @Override
    public void deleteAllEmployees() {
        repository.deleteAll();
    }

    @Override
    public void deleteEmployees(Iterable<Employee> employees) {
        repository.deleteAll(employees);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        repository.delete(employee);
    }
}
