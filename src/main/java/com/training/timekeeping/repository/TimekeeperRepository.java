package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee_Timekeeper;
import com.training.timekeeping.model.Timekeeper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Repository
public interface TimekeeperRepository extends CrudRepository<Timekeeper, Employee_Timekeeper>, TimekeeperRepositoryCustom {
    // get time check-in
//    @Query(value = "SELECT * FROM ", nativeQuery = true)
//    LocalDate getTimeCheckOut(String employeeId, LocalDate date);
    // get hours work
    // get hours late


}
