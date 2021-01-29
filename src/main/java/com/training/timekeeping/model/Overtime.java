package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Overtime")
@Data
@NoArgsConstructor
@AllArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "id.employee", joinColumns = @JoinColumn(name = "employee_id"))
})
public class Overtime {

    @AttributeOverrides({
            @AttributeOverride(name = "dateOT", column = @Column(name = "date_OT", columnDefinition = "DATE"))
    })
    @EmbeddedId
    private Employee_Overtime id;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;

}
