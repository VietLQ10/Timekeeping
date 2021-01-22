package com.training.timekeeping.repository;

import com.training.timekeeping.model.Timekeeper;

import java.util.Date;
import java.util.List;

public interface TimekeeperRepositoryCustom {
    List<Timekeeper> getTimekeepers (String employeeId, Date date);
}
