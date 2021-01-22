package com.training.timekeeping.repository.impl;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.TimekeeperRepositoryCustom;

import java.util.Date;
import java.util.List;

public class TimekeeperRepositoryImpl implements TimekeeperRepositoryCustom {
    @Override
    public List<Timekeeper> getTimekeepers(String employeeId, Date date) {
        return null;
    }
}
