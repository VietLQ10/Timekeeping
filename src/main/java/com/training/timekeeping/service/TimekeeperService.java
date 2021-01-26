package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public interface TimekeeperService {

    //void save (Timekeeper timekeeper);

    // get time check-in
    LocalDate getTimeCheckIn(String employeeId, LocalDate date);
    // get time check-out
    LocalDate getTimeCheckOut(String employeeId);
    // get hours work
    // get hours late

    List<TimekeeperDTO> getTimekeepers();
}
