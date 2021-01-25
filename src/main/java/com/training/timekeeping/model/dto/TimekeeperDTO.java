package com.training.timekeeping.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimekeeperDTO {

    private String name;

    private LocalDate date;

    private boolean checkIn;

    private boolean checkOut;

}
