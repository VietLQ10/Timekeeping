package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee_Overtime implements Serializable {

    @ManyToOne
//    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date dateOT;

}
