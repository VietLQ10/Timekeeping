package com.training.timekeeping.controller;

import com.training.timekeeping.config.JwtRequestFilter;
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

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @GetMapping("/get-all-timekeepers")
    public ResponseEntity<?> getAll() {
        List<TimekeeperDTO> timekeepers = service.getAllTimekeepers();
        return ResponseEntity.ok(timekeepers);
    }

    @GetMapping("/get-timekeepers-employee")
    public ResponseEntity<?> getTimekeepersEmployee() {
        List<TimekeeperDTO> timekeepers = service.getTimekeepersByEmployee(jwtRequestFilter.getEmail());
        return ResponseEntity.ok(timekeepers);
    }

    @GetMapping("/get-time-check-in")
    public ResponseEntity<?> getTimeCheckIn(@RequestParam(name = "email") String email,
                                            @RequestParam(name = "date") String date) {
        LocalTime timeCheckIn = service.getTimeCheckIn(email, LocalDate.parse(date));
        return ResponseEntity.ok(timeCheckIn);
    }

    @GetMapping("/get-time-check-out")
    public ResponseEntity<?> getTimeCheckOut(@RequestParam(name = "email") String email,
                                            @RequestParam(name = "date") String date) {
        LocalTime timeCheckOut = service.getTimeCheckOut(email, LocalDate.parse(date));
        return ResponseEntity.ok(timeCheckOut);
    }

    @GetMapping("/get-hours-of-work")
    public ResponseEntity<?> getHoursOfWork(@RequestParam(name = "id") String employeeId,
                                             @RequestParam(name = "date") String date) {
        long hoursOfWork = service.getHoursOfWorkByMonth(employeeId, date);
        return ResponseEntity.ok(Util.convertTime(hoursOfWork));
    }

//    @GetMapping("/get-hours-of-work-month")
//    public ResponseEntity<?> getHoursOfWorkMonth(@RequestParam(name = "id") String employeeId,
//                                            @RequestParam(name = "date") String date) {
//        long hoursOfWorkByMonth = service.getHoursOfWorkByMonth(employeeId, date);
//        return ResponseEntity.ok(Util.convertTime(hoursOfWorkByMonth));
//    }

    @GetMapping("/get-hours-of-late")
    public ResponseEntity<?> getHoursOfLate(@RequestParam(name = "id") String employeeId,
                                            @RequestParam(name = "date") String date) {
        long timeCheckOut = service.getHoursOfLateByMonth(employeeId, date);
        return ResponseEntity.ok(Util.convertTime(timeCheckOut));
    }
}
