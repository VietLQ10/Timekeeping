package com.training.timekeeping.repository;

import com.training.timekeeping.model.Account;
import com.training.timekeeping.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Employee, String> {

    Optional<Employee> findByEmailAndPassword(String email, String password);
}
