package com.training.timekeeping.repository.impl;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.repository.TimekeeperRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TimekeeperRepositoryCustomImpl implements TimekeeperRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public List<Timekeeper> getTimekeepers(String employeeId, Date date) {
        return null;
    }

    @Override
    public LocalDate getTimeCheckIn(String employeeId, LocalDate date) {
        return null;
    }

    @Override
    public LocalDate getTimeCheckOut(String employeeId) {
        return null;
    }

    @Override
    public List<Timekeeper> getTimekeeper() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Timekeeper> query = builder.createQuery(Timekeeper.class);
        Root<Timekeeper> root = query.from(Timekeeper.class);
        //Join<Object, Object> join = root.join("employee", JoinType.INNER);
        query.select(root);
        TypedQuery<Timekeeper> query1 = em.createQuery(query.select(root));
        List<Timekeeper> resultList = query1.getResultList();
        return resultList;
    }
}
