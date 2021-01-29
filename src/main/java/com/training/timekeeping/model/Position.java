package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Position")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position {

    @Id
    @Column(name = "position_id", columnDefinition = "VARCHAR(5)")
    private String positionId;

    @Column(name = "position_name", columnDefinition = "NVARCHAR(100)", nullable = false, unique = true)
    private String positionName;

    @OneToMany(mappedBy = "position")
    @ToString.Exclude private Set<Employee> employee;

    public Position(String positionId) {
        this.positionId = positionId;
    }
}
