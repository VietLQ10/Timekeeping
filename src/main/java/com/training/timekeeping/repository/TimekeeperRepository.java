package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee_Timekeeper;
import com.training.timekeeping.model.Timekeeper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimekeeperRepository extends CrudRepository<Timekeeper, Employee_Timekeeper>, TimekeeperRepositoryCustom{

    @Query("SELECT t FROM Timekeeper t WHERE t.timekeeperId.employee.employeeId = ?1")
    List<Timekeeper> findById(String employeeId);

    @Query("SELECT t FROM Timekeeper t WHERE t.timekeeperId.employee.employeeId = ?1" +
            "AND DATE_FORMAT(t.timekeeperId.timeCheck, '%Y-%m') = ?2")
    List<Timekeeper> getWorkByMonth(String id, String date);

//    @Query("SELECT t FROM Timekeeper t WHERE t.timekeeperId.employee.employeeId = ?1" +
//            "AND to_char, 'yyyy-MM-dd') = ?2")
//    List<Timekeeper> getWorkBydate(String id, String date);
}
