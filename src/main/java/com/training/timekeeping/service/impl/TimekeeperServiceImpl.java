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

import java.time.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public long getHoursOfWorkByMonth(String employeeId, String date) {
        List<Timekeeper> result = new ArrayList<>();
        repository.findById(employeeId).forEach(timekeeper -> {
            if (timekeeper.getTimekeeperId().getTimeCheck().toString().contains(date)) {
                result.add(timekeeper);
            }
        });

        long sum = 0;
        for (int i = 0; i < result.size(); i = i + 2) {
            long seconds = Duration.between(result.get(i).getTimekeeperId().getTimeCheck(), result.get(i + 1).getTimekeeperId().getTimeCheck()).getSeconds();
            sum += seconds;
        }
        return sum;
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

    private long calHours(LocalTime startTime, LocalTime endTime) {
        return Math.abs(Duration.between(startTime, endTime).getSeconds());
    }

    @Override
    public long getHoursOfLateByMonth(String employeeId, String date) {
        long sum = 0;
        int i = 0;
        Employee employee = service.getEmployee(Constant.EMPLOYEE_ID, employeeId);
        List<Timekeeper> checkIn = new ArrayList<>();
        List<Timekeeper> timekeepers = repository.findById(employeeId);
        for (Timekeeper timekeeper : timekeepers) {
            if (timekeeper.getTimekeeperId().getTimeCheck().toString().contains(date)) {
                if (date.equalsIgnoreCase(LocalDate.from(timekeeper.getTimekeeperId().getTimeCheck()).toString())){
                    return calHours(getTimeCheckIn(employeeId, LocalDate.parse(date)), employee.getTimeStartWork());
                }
                else {

                    if (checkIn.size() == 0) {
                        checkIn.add(timekeeper);

                    } else {
                        String localDate = LocalDate.from(timekeeper.getTimekeeperId().getTimeCheck()).toString();
//                        for (Timekeeper t : checkIn) {
//                            if (! localDate.equalsIgnoreCase(LocalDate.from(t.getTimekeeperId().getTimeCheck()).toString())) {
//                                checkIn.add(timekeeper);
//                            }
//                        }
//                        checkIn.forEach(timekeeper1 -> {
//                            if (! timekeeper1.getTimekeeperId().toString().contains(localDate)) {
//                                checkIn.add(timekeeper);
//                            }
//                        });
                        for (; i < checkIn.size(); i++) {
                            if (! checkIn.get(i).getTimekeeperId().toString().contains(localDate)) {
                                checkIn.add(timekeeper);
                                i++;
                            }
                        }
                    }

                }

            }
        }

        for (Timekeeper t : checkIn) {
            sum += calHours(LocalTime.from(t.getTimekeeperId().getTimeCheck()), employee.getTimeStartWork());
        }
        return sum;
    }

    @Override
    public List<TimekeeperDTO> getAllTimekeepers() {
        List<TimekeeperDTO> timekeeperDTOS = new ArrayList<>();
        repository.findAll().forEach(timekeeper -> {
            timekeeperDTOS.add(new TimekeeperDTO(timekeeper.getTimekeeperId().getEmployee().getName(), timekeeper.getTimekeeperId().getTimeCheck()));
        });
        return timekeeperDTOS;
    }

    @Override
    public List<TimekeeperDTO> getTimekeepersByEmployee(String email) {
        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, email);
        List<TimekeeperDTO> timekeeperDTOS = new ArrayList<>();
        repository.findById(employee.getEmployeeId()).forEach(timekeeper -> {
            timekeeperDTOS.add(new TimekeeperDTO(timekeeper.getTimekeeperId().getEmployee().getName(), timekeeper.getTimekeeperId().getTimeCheck()));
        });
        return timekeeperDTOS;
    }
}
