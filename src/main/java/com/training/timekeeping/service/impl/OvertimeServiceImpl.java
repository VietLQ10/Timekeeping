package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Overtime;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.OvertimeRepository;
import com.training.timekeeping.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeRepository repository;

    private Overtime getByID(String employeeId, LocalDate date) {
        List<Overtime> overtimes = repository.findById(employeeId);
        if (overtimes.size() == 0) return null;
        for (Overtime overtime : overtimes) {
            if(date.equals(overtime.getId().getDateOT())) {
                return overtime;
            }
        }
        return null;
    }

    @Override
    public long getOvertime(String employeeId, Object obj) {
        Overtime result = getByID(employeeId, (LocalDate) obj);
        if(result != null) {
            return Math.abs(Duration.between(result.getTimeStart(), result.getTimeEnd()).getSeconds());
        }
        return 0;
    }
}
