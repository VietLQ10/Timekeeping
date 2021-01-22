package com.training.timekeeping.controller;

import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.service.EmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @Autowired
    private EmployeeService service;

//    @PostMapping("/login")
//    public ResponseEntity<?> login (@RequestBody Account account) {
//        Account accountData = service.findAccount(account);
//        if (accountData != null) {
//            return ResponseEntity.ok(accountData);
//        }
//        return (ResponseEntity<?>) ResponseEntity.notFound();
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) throws ServletException{
        String jwtToken = "";
        if (account.getEmail() == null || account.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }

        Employee employee = service.findEmployee("email", account.getEmail());

        if (employee == null) {
            throw new ServletException("User email not found.");
        }

        String password = employee.getPassword();
        if (!account.getPassword().equals(password)) {
            throw new ServletException("Invalid login. Please check your name and password.");
        }

        jwtToken = Jwts.builder().setSubject(account.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return ResponseEntity.ok(jwtToken);
    }
}
