package com.training.timekeeping.controller;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/find-all")
    public ResponseEntity<?> findAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(employees);
    }

    // get list employees
    @GetMapping("find-list")
    public ResponseEntity<?> findEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        return ResponseEntity.ok(employees);
    }

//    @GetMapping("find")
//    public ResponseEntity<?> findEmployee(@RequestParam(name = "key") String key,
//                                          @RequestParam(name = "data") Object data) {
//        List<Employee> employees = service.getEmployees(key, data);
//        return ResponseEntity.ok(employees);
//    }

    // delete all employees
    @DeleteMapping("delete-all")
    public ResponseEntity<?> deleteAllEmployees(){
        service.deleteAllEmployees(Role.ADMIN);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete list employees
    @DeleteMapping("delete-list")
    public ResponseEntity<?> deleteEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        service.deleteEmployees(employees, Role.ADMIN);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete a employee

    // edit a employee

    // add a employee
}
