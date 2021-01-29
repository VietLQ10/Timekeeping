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
    LocalTime getTimeCheckIn(String email, LocalDate date);

    // get time check-out
    LocalTime getTimeCheckOut(String email, LocalDate date);

    // get hours work
    long getHoursOfWork(String employeeId, Object obj);

    long getHoursOfWorkByMonth(String employeeId,  String date);

    // get hours late
    long getHoursLate(String employeeId, Object obj);

    // get hours late month
    long getHoursOfLateByMonth(String employeeId,  String date);

    List<TimekeeperDTO> getAllTimekeepers();

    List<TimekeeperDTO> getTimekeepersByEmployee(String email);
}
