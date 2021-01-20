package com.training.timekeeping.service;

import com.training.timekeeping.model.Account;

import java.util.Optional;

public interface LoginService {

    Account findAccount(Account account);
}
