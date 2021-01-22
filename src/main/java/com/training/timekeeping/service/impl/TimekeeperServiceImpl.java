package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.TimekeeperRepository;
import com.training.timekeeping.service.TimekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;

public class TimekeeperServiceImpl implements TimekeeperService {

    @Autowired
    private TimekeeperRepository repository;
    
    @Override
    public void save(Timekeeper timekeeper) {
        repository.save(timekeeper);
    }

    @Override
    public Timer getHoursOfWork(String employeeId, Date date) {

        return null;
    }

    @Override
    public Timer getHoursOfLate(String employeeId) {
        return null;
    }
}
