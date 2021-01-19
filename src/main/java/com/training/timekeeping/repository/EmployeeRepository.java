package com.training.timekeeping.repository;

import com.training.timekeeping.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    // get employee by email
    Optional<Employee> findByEmail(String email);

    //get employees by name
    List<Employee> findByName(String name);
}
