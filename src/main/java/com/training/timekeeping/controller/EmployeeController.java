package com.training.timekeeping.controller;

import com.sun.net.httpserver.HttpServer;
import com.training.timekeeping.Utils.Constant;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // get all employees
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(employees);
    }

    // get list employees
    @GetMapping("get-list")
    public ResponseEntity<?> getEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        return ResponseEntity.ok(employees);
    }

    // delete all employees
    @DeleteMapping("delete-all")
    public ResponseEntity<?> deleteAllEmployees(){
        service.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete list employees
    @DeleteMapping("delete-list")
    public ResponseEntity<?> deleteEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        service.deleteEmployees(employees);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // delete a employee

    // edit a employee

    // add a employee
}
