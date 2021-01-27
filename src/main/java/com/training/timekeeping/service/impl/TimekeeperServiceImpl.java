package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;
import com.training.timekeeping.repository.TimekeeperRepository;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.service.TimekeeperService;
import com.training.timekeeping.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimekeeperServiceImpl implements TimekeeperService {

    @Autowired
    private TimekeeperRepository repository;

    @Autowired
    private EmployeeService service;

    private List<Timekeeper> getByID(String employeeId, LocalDate date) {
        List<Timekeeper> timekeepers = repository.findById(employeeId);
        List<Timekeeper> result = new ArrayList<>();

        if (timekeepers.size() == 0) return null;
        timekeepers.forEach(timekeeper -> {
            if(date.equals(LocalDate.from(timekeeper.getTimekeeperId().getTimeCheck()))) {
                result.add(timekeeper);
            }
        });

        if (result.size() == 0) return null;
        return result;
    }

    @Override
    public LocalTime getTimeCheckIn(String employeeId, LocalDate date) {
        List<Timekeeper> result = getByID(employeeId, date);
        if (result != null) {
            return LocalTime.from(result.get(0).getTimekeeperId().getTimeCheck());
        }
        return null;
    }

    @Override
    public LocalTime getTimeCheckOut(String employeeId, LocalDate date) {
        List<Timekeeper> result = getByID(employeeId, date);
        if(result != null) {
            return LocalTime.from(result.get(result.size() - 1).getTimekeeperId().getTimeCheck());
        }
        return null;
    }

    @Override
    public long getHoursOfWork(String employeeId, Object obj) {
        List<Timekeeper> result = getByID(employeeId, (LocalDate) obj);
        long sum = 0;
        if (result != null) {
            for (int i = 0; i < result.size(); i = i + 2) {
                long seconds = Duration.between(result.get(i).getTimekeeperId().getTimeCheck(), result.get(i + 1).getTimekeeperId().getTimeCheck()).getSeconds();
                sum += seconds;
            }
            return sum;
        }
        return 0;
    }

    @Override
    public long getHoursLate(String employeeId, Object obj) {
        List<Timekeeper> result = getByID(employeeId, (LocalDate) obj);
        Employee employee = service.getEmployee(Constant.EMPLOYEE_ID, employeeId);
        if (employee == null) {
            return -1;
        }

        if (result != null) {
            return Duration.between(getTimeCheckIn(employeeId, (LocalDate) obj), employee.getTimeStartWork()).getSeconds();
        }

        return 0;
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
