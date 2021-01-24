package com.training.timekeeping.controller;

import com.training.timekeeping.config.JwtTokenUtil;
import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.dto.JwtResponse;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.utils.Constant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmployeeService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account, HttpServletResponse response) throws Exception {
        authenticate(account.getEmail(), account.getPassword());

        final UserDetails userDetails = service
                .loadUserByUsername(account.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(Constant.TOKEN_HEADER, Constant.TOKEN_PREFIX + token);
        return ResponseEntity.ok(account.getEmail());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
