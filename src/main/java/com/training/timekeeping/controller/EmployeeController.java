package com.training.timekeeping.controller;

import com.training.timekeeping.config.JwtRequestFilter;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.utils.Constant;
import com.training.timekeeping.utils.Role;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // get all employees
    @GetMapping("/find-all")
    public ResponseEntity<?> findAllEmployee() {
       String email = jwtRequestFilter.getEmail();

        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(email);
    }

    // get list employees
    @GetMapping("find-list")
    public ResponseEntity<?> findEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        return ResponseEntity.ok(employees);
    }

    // delete list employees
    @DeleteMapping("delete-list")
    public ResponseEntity<?> deleteEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        boolean isDelete = service.deleteEmployees(employees, jwtRequestFilter.getEmail());
        if (isDelete) {
            return ResponseEntity.ok("Delete success!");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    // delete a employee

    // edit a employee

    // add a employee
    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee emp = service.addEmployee(employee);
        return ResponseEntity.ok(emp);
    }
}
