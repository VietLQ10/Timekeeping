package com.training.timekeeping.repository;

import com.training.timekeeping.model.Timekeeper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface TimekeeperRepositoryCustom {
    List<Timekeeper> getTimekeeper();

    // get time check-in


    // get time check-out

    // get hours work

    // get hours late

}
