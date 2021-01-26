package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;
import com.training.timekeeping.repository.TimekeeperRepository;
import com.training.timekeeping.service.TimekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class TimekeeperServiceImpl implements TimekeeperService {

    @Autowired
    private TimekeeperRepository repository;

    @Override
    public LocalDate getTimeCheckIn(String employeeId, LocalDate date) {
        return null;
    }

    @Override
    public LocalDate getTimeCheckOut(String employeeId) {
        return null;
    }

    @Override
    public List<TimekeeperDTO> getTimekeepers() {
        List<TimekeeperDTO> timekeeperDTOS = new ArrayList<>();
        repository.getTimekeeper().forEach(timekeeper -> {
            timekeeperDTOS.add(new TimekeeperDTO(timekeeper.getTimekeeperId().getEmployee().getName(), timekeeper.getTimekeeperId().getTimeCheck()));
        });
        return timekeeperDTOS;
    }

}
