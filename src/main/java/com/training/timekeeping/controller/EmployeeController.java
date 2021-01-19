package com.training.timekeeping.controller;

import com.sun.net.httpserver.HttpServer;
import com.training.timekeeping.Utils.Constant;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(employees);
    }

    // get list employees
    @GetMapping(value = "/get_employees", produces = "application/json")
    public ResponseEntity<?> getEmployees(@RequestParam String data, HttpServletRequest httpRequest) {
        //List<Employee> employees = service.getEmployees(Constant.EMPLOYEE_NAME, data);

        String requestURI = httpRequest.getQueryString();
        return ResponseEntity.ok(requestURI);
    }

    @GetMapping("getEmployees")
    public ResponseEntity<?> getEmployees(@RequestParam(name = "id") String data) {
        //List<Employee> employees = service.getEmployees(Constant.EMPLOYEE_NAME, data);


        return ResponseEntity.ok(data);
    }




    //get a employee

    // delete all employees

    // delete list employees

    // delete a employee

    // edit a employee

    // add a employee
}
