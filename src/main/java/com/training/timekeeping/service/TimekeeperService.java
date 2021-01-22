package com.training.timekeeping.service;

import com.training.timekeeping.model.Timekeeper;

import java.util.Date;
import java.util.Timer;

public interface TimekeeperService {

    void save (Timekeeper timekeeper);

    // hours of work
    Timer getHoursOfWork(String employeeId, Date date);

    // hours of late
    Timer getHoursOfLate(String employeeId);
}
