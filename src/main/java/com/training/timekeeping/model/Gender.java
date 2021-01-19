package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Gender")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genderId;

    /*
    * true: male
    * false: female
    *
    * */
    @Column(name = "gender", columnDefinition = "VARCHAR(6)", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "gender")
    @ToString.Exclude private Set<Employee> employee;
}
