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

    /**
    * creat
    *
    * */
    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        boolean isCreate = service.createUser(jwtRequestFilter.getEmail(), employee);
        if (isCreate) {
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * retrieve
     *
     * */
    // get all employees
    @GetMapping("/get-all-employees")
    public ResponseEntity<?> findAllEmployee() {
        String email = jwtRequestFilter.getEmail();

        List<Employee> employees = new ArrayList<>();
        service.getAllEmployee().forEach(employee -> {
            employees.add(employee);
        });
        return ResponseEntity.ok(employees);
    }

    // get list employees
    @GetMapping("get-employees")
    public ResponseEntity<?> findEmployees(@RequestParam(name = "key") String key,
                                           @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        return ResponseEntity.ok(employees);
    }

    // get a employee
    @GetMapping("get-employee")
    public ResponseEntity<?> findEmployee(@RequestParam(name = "key") String key,
                                          @RequestParam(name = "data") Object data) {
        Employee employee = service.getEmployee(key, data);
        return ResponseEntity.ok(employee);
    }

    /**
     * update
     *
     * */
    @PutMapping("/update-employee")
    public ResponseEntity<?> updateEmployee(@RequestParam(name = "key") String key,
                                            @RequestParam(name = "data") Object data) {
        Employee employee = service.getEmployee(key, data);
        boolean isUpdate = service.updateEmployee(jwtRequestFilter.getEmail(), employee);
        if(isUpdate) {
            return ResponseEntity.ok(employee);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * delete
     *
     * */
    // delete list employees
    @DeleteMapping("/delete-employees")
    public ResponseEntity<?> deleteEmployees(@RequestParam(name = "key") String key,
                                             @RequestParam(name = "data") Object data) {
        List<Employee> employees = service.getEmployees(key, data);
        boolean isDelete = service.deleteEmployees(jwtRequestFilter.getEmail(), employees);
        if (isDelete) {
            return ResponseEntity.ok("Delete success!");
        }
        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    // delete a employee
    @DeleteMapping("/delete-employee")
    public ResponseEntity<?> deleteEmployee(@RequestParam(name = "key") String key,
                                            @RequestParam(name = "data") String data) {
        Employee employee = service.getEmployee(key, data);
        boolean isDelete = service.deleteEmployee(jwtRequestFilter.getEmail(), employee);
        if (isDelete) {
            return ResponseEntity.ok("Delete success!");
        }
        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
}
