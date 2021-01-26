package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Timekeeper")
@Data
@NoArgsConstructor
@AllArgsConstructor
@AssociationOverride(name = "timekeeperId.employee", joinColumns = @JoinColumn(name = "employee_id"))
public class Timekeeper {
    @AttributeOverride(name = "timeCheck", column = @Column(name = "time_check", columnDefinition = "DATETIME", nullable = false))

    @EmbeddedId
    private Employee_Timekeeper timekeeperId;
}

