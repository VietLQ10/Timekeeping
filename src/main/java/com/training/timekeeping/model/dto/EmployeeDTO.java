package com.training.timekeeping.model.dto;

import com.training.timekeeping.model.Department;
import com.training.timekeeping.model.Gender;
import com.training.timekeeping.model.Position;

import java.time.LocalTime;

public class EmployeeDTO {
    private String id;

    private String name;

    private String email;

    private String department;

    private String position;

    private String gender;

    private int numHoursOff;

    private int numRemaining;

    private long overtime;

    private long hoursLate;

    private long hoursWork;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, String email, String department, String position, String gender, int numHoursOff, int numRemaining) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.position = position;
        this.gender = gender;
        this.numHoursOff = numHoursOff;
        this.numRemaining = numRemaining;
    }

    public EmployeeDTO(String id, String name, String email, String department, String position, String gender, int numHoursOff, int numRemaining, long overtime, long hoursLate, long hoursWork) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.position = position;
        this.gender = gender;
        this.numHoursOff = numHoursOff;
        this.numRemaining = numRemaining;
        this.overtime = overtime;
        this.hoursLate = hoursLate;
        this.hoursWork = hoursWork;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNumHoursOff() {
        return numHoursOff;
    }

    public void setNumHoursOff(int numHoursOff) {
        this.numHoursOff = numHoursOff;
    }

    public int getNumRemaining() {
        return numRemaining;
    }

    public void setNumRemaining(int numRemaining) {
        this.numRemaining = numRemaining;
    }

    public long getOvertime() {
        return overtime;
    }

    public void setOvertime(long overtime) {
        this.overtime = overtime;
    }

    public long getHoursLate() {
        return hoursLate;
    }

    public void setHoursLate(long hoursLate) {
        this.hoursLate = hoursLate;
    }

    public long getHoursWork() {
        return hoursWork;
    }

    public void setHoursWork(long hoursWork) {
        this.hoursWork = hoursWork;
    }
}
