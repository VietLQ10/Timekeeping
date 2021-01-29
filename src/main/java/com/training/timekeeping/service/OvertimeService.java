package com.training.timekeeping.service;

import com.training.timekeeping.model.Employee_Overtime;
import com.training.timekeeping.model.Overtime;

import java.time.LocalDate;
import java.util.List;

public interface OvertimeService {

//    long getOvertime (String employeeId, Object obj);

//    Overtime getOvertime (String employeeId, Employee_Overtime id);

    // get all overtimes
    List<Overtime> getOvertimes();

    // get all overtimes of employee
    List<Overtime> getOvertimesByEmployee(String email);

    // get a overtime
    Overtime getOvertime(String email, LocalDate date);

    // create overtime
    boolean createOvertime(String email, Overtime overtime);

    // edit overtime
    boolean editOvertime(String email, Overtime overtime);


}
