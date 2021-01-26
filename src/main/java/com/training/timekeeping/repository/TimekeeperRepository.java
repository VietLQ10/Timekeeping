package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee_Timekeeper;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Repository
public interface TimekeeperRepository extends CrudRepository<Timekeeper, Employee_Timekeeper>, TimekeeperRepositoryCustom{
//    // get time check-in
//    @Query("FROM Timekeeper t ORDER BY t.getTimekeeperId.getTimecheck ")
//    LocalTime findByOrderBy();
//    @Query("SELECT e.getName, t.getTimekeeperId.getTimeCheck FROM Employee e JOIN Timekeeper t ON e.getEmployeeId = t.getTimekeeperId.getEmployee.getEmployeeId")
//    @Query(value = "SELECT e.name, t.time_check FROM employee e JOIN timekeeper t ON e.employee_id = t.employee_id", nativeQuery = true)
//    @Query("FROM Timekeeper ")

    // get hours work String employeeId, LocalDate date
    // get hours late


}
