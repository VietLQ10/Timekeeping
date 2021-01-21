package com.training.timekeeping.controller;

import com.training.timekeeping.model.Account;
import com.training.timekeeping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    @Autowired
    private LoginService service;

//    @PostMapping("/login")
//    public ResponseEntity<?> login (@RequestBody Account account) {
//        Account accountData = service.findAccount(account);
//        if (accountData != null) {
//            return ResponseEntity.ok(accountData);
//        }
//        return (ResponseEntity<?>) ResponseEntity.notFound();
//    }
}
