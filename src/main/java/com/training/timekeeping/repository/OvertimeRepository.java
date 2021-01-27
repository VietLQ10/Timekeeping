package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee_Overtime;
import com.training.timekeeping.model.Overtime;
import com.training.timekeeping.model.Timekeeper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OvertimeRepository extends CrudRepository<Overtime, Employee_Overtime> {
    @Query("SELECT o FROM Overtime o WHERE o.id.employee.employeeId = ?1")
    List<Overtime> findById(String employeeId);
}
