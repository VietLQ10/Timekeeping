package com.training.timekeeping.model.dto;

import com.training.timekeeping.model.Department;
import com.training.timekeeping.model.Gender;
import com.training.timekeeping.model.Position;

import java.time.LocalTime;

public class EmployeeDTO {
    private String id;

    private String name;

    private String email;

    private String password;

    private Department department;

    private Position position;

    private Gender gender;

    private int numDayoff;

    private int numRemaining;

    private LocalTime timeStartWork;

    private LocalTime timeEndWork;

    private LocalTime timeBreak;

    private String role;

    public EmployeeDTO(String id, String email, Department department, Position position, Gender gender, int numDayoff,
                       LocalTime timeStartWork, LocalTime timeEndWork, LocalTime timeBreak, String role) {
        this.id = id;
        this.email = email;
        this.department = department;
        this.position = position;
        this.gender = gender;
        this.numDayoff = numDayoff;
        this.timeStartWork = timeStartWork;
        this.timeEndWork = timeEndWork;
        this.timeBreak = timeBreak;
        this.role = role;
    }

    
}
