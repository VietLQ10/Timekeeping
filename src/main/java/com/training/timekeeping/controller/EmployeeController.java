package com.training.timekeeping.controller;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // get all employees
    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(employees);
    }

    // get list employees
    @GetMapping("/get_employees")
    public ResponseEntity<?> getEmployees(@RequestParam String type, Object data) {
        List<Employee> employees = service.getEmployees(type, data);
        return ResponseEntity.ok(employees);
    }

    //get a employee

    // delete all employees

    // delete list employees

    // delete a employee

    // edit a employee

    // add a employee
}
