package com.training.timekeeping.service;

import com.training.timekeeping.model.dto.Account;

public interface LoginService {

    Account findAccount(Account account);
}
