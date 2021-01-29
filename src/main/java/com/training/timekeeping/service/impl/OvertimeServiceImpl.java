package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Employee_Overtime;
import com.training.timekeeping.model.Overtime;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.OvertimeRepository;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.service.OvertimeService;
import com.training.timekeeping.utils.Constant;
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

    @Autowired
    private EmployeeService service;

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

//    @Override
//    public long getOvertime(String employeeId, Object obj) {
//        Overtime result = getByID(employeeId, (LocalDate) obj);
//        if(result != null) {
//            return Math.abs(Duration.between(result.getTimeStart(), result.getTimeEnd()).getSeconds());
//        }
//        return 0;
//    }

    @Override
    public Overtime getOvertime(String email, LocalDate date) {
        List<Overtime> overtimesByEmployee = getOvertimesByEmployee(email);
        for (Overtime overtime : overtimesByEmployee) {
            if (date.equals(overtime.getId().getDateOT())) {
                return overtime;
            }
        }
        return null;
    }

    @Override
    public List<Overtime> getOvertimes() {
        List<Overtime> overtimes = new ArrayList<>();
        repository.findAll().forEach(overtime -> {
            overtimes.add(overtime);
        });
        return overtimes;
    }

    @Override
    public List<Overtime> getOvertimesByEmployee(String email) {
        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, email);
        List<Overtime> overtimes = repository.findById(employee.getEmployeeId());
        return overtimes;
    }

    @Override
    public boolean createOvertime(String email, Overtime overtime) {
        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, email);
        overtime.setId(new Employee_Overtime(employee));
        repository.save(overtime);
        return true;
    }

    @Override
    public boolean editOvertime(String email, Overtime overtime) {
        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, email);
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
            Overtime overtime1 = getOvertime(overtime.getId().getEmployee().getEmail(), overtime.getId().getDateOT());
            if (overtime1 != null) {
                repository.save(overtime);
                return true;
            }
        }
        return false;
    }
}
