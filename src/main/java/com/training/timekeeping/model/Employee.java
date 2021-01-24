package com.training.timekeeping.model;

import com.training.timekeeping.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
@Table(name = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id", columnDefinition = "VARCHAR(5)")
    private String employeeId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String name;

    @Column(name = "email", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @Column(name = "num_dayoff", columnDefinition = "INT DEFAULT 1", nullable = false)
    private int numDayoff;

    @Column(name = "num_remaining", columnDefinition = "INT DEFAULT 1", nullable = false)
    private int numRemaining;

    @Column(name = "time_start_work")
    private LocalTime timeStartWork;

    @Column(name = "time_end_work")
    private LocalTime timeEndWork;

    @Column(name = "time_break")
    private LocalTime timeBreak;

    @Column(name = "role", columnDefinition = "VARCHAR(10)")
    private String role;

    @OneToMany(mappedBy = "id.employee")
    @ToString.Exclude private  List<Overtime> overtimes;

    @OneToMany (mappedBy = "employee")
    @ToString.Exclude private List<Request> request;

    @OneToMany(mappedBy = "timekeeperId.employee")
    @ToString.Exclude private List<Timekeeper> timekeepers;

}
