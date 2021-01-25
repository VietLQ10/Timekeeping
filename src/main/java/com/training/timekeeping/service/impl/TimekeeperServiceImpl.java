package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.TimekeeperRepository;
import com.training.timekeeping.service.TimekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class TimekeeperServiceImpl implements TimekeeperService {

    @Autowired
    private TimekeeperRepository repository;

    @Override
    public LocalDate getTimeCheckIn(String employeeId, LocalDate date) {
        return null;
    }

    @Override
    public LocalDate getTimeCheckOut(String employeeId) {
        return null;
    }

    @Override
    public List<Timekeeper> getTimekeeper() {
        return repository.getTimekeeper();
    }
}
