package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee_Timekeeper;
import com.training.timekeeping.model.Timekeeper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Timer;

//@Repository
public interface TimekeeperRepository extends CrudRepository<Timekeeper, Employee_Timekeeper> {

    // time check-in
//    @Query("SELECT t.timekeeperId.timeCheck " +
//            "FROM Timekeeper t " +
//            "WHERE t.timekeeperId.employee.employeeId = ?1 AND t.timekeeperId.timeCheck = ?2" +
//            "ORDER BY t.timekeeperId.timeCheck ASC")
//    Date getTimeCheckIn(String employeeId, String date);

    // time check-out

    // get hours of work

    // get hours of late

//    List<Timekeeper> getTimekeepers (String employeeId, String date);


}
