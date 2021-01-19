package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
    @Temporal(TemporalType.TIME)
    private Date timeStart;

    @Column(name = "time_end", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date timeEnd;

//    @ManyToOne
//    @MapsId("employeeId")
//    private Employee employee;

}
