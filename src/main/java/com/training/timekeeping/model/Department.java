package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @Column(name = "department_id", columnDefinition = "VARCHAR(5)")
    private String departmentId;

    @Column(name = "department_name", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String departmentName;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude private Set<Employee> employee;

    public Department(String departmentId) {
        this.departmentId = departmentId;
    }
}
