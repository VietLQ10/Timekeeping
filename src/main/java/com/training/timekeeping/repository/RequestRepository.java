package com.training.timekeeping.repository;

import com.training.timekeeping.model.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

    @Query("FROM Request r WHERE r.employee.email = ?1")
    List<Request> findByEmployeeId(String email);
}
