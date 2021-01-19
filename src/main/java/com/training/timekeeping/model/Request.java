package com.training.timekeeping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "start_request", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startRequest;

    @Column(name = "end_request", columnDefinition = "DATETIME", nullable = false)
    private Date endRequest;

    @Column(name = "content", columnDefinition = "VARCHAR(1000)", nullable = false)
    private String content;
}
