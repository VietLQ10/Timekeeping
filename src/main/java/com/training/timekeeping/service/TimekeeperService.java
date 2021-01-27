package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public interface TimekeeperService {

    //void save (Timekeeper timekeeper);

    // get time check-in
    LocalTime getTimeCheckIn(String employeeId, LocalDate date);

    // get time check-out
    LocalTime getTimeCheckOut(String employeeId, LocalDate date);

    // get hours work
    long getHoursOfWork(String employeeId, Object obj);

    // get hours late
    long getHoursLate(String employeeId, Object obj);

    List<TimekeeperDTO> getTimekeepers();
}
