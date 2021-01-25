package com.training.timekeeping.repository;

import com.training.timekeeping.model.Timekeeper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TimekeeperRepositoryCustom {
    List<Timekeeper> getTimekeepers (String employeeId, Date date);

    // get time check-in
    LocalDate getTimeCheckIn(String employeeId, LocalDate date);
    // get time check-out
    LocalDate getTimeCheckOut(String employeeId);
    // get hours work
    // get hours late

    // retrieve timekeeper
    List<Timekeeper> getTimekeeper();
}
