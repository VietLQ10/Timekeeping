package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Account;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.repository.LoginRepository;
import com.training.timekeeping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository repository;

    @Override
    public Account findAccount(Account account) {

        Optional<Employee> optional = repository.findByEmailAndPassword(account.getEmail(), account.getPassword());

        if (optional.isPresent()) {
            return new Account(optional.get().getEmail(), optional.get().getPassword());
        }
        return null;
    }
}
