package com.training.timekeeping.controller;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;
import com.training.timekeeping.service.TimekeeperService;
import com.training.timekeeping.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/timekeepers")
public class TimekeeperController {

    @Autowired
    private TimekeeperService service;

    @GetMapping("/get-all-timekeepers")
    public ResponseEntity<?> getAll() {
        List<TimekeeperDTO> timekeepers = service.getTimekeepers();
        return ResponseEntity.ok(timekeepers);
    }

    @GetMapping("/get-time-check-in")
    public ResponseEntity<?> getTimeCheckIn(@RequestParam(name = "id") String employeeId,
                                            @RequestParam(name = "date") String date) {
        LocalTime timeCheckIn = service.getTimeCheckIn(employeeId, LocalDate.parse(date));
        return ResponseEntity.ok(timeCheckIn);
    }

    @GetMapping("/get-time-check-out")
    public ResponseEntity<?> getTimeCheckOut(@RequestParam(name = "id") String employeeId,
                                            @RequestParam(name = "date") String date) {
        LocalTime timeCheckOut = service.getTimeCheckOut(employeeId, LocalDate.parse(date));
        return ResponseEntity.ok(timeCheckOut);
    }

    @GetMapping("/get-hours-of-work")
    public ResponseEntity<?> getHoursOfWork(@RequestParam(name = "id") String employeeId,
                                             @RequestParam(name = "date") String date) {
        long timeCheckOut = service.getHoursOfWork(employeeId, LocalDate.parse(date));
        return ResponseEntity.ok(Util.convertTime(timeCheckOut));
    }

    @GetMapping("/get-hours-of-late")
    public ResponseEntity<?> getHoursOfLate(@RequestParam(name = "id") String employeeId,
                                            @RequestParam(name = "date") String date) {
        long timeCheckOut = service.getHoursLate(employeeId, LocalDate.parse(date));
        return ResponseEntity.ok(Util.convertTime(timeCheckOut));
    }
}
