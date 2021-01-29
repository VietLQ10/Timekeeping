package com.training.timekeeping.controller;

import com.training.timekeeping.config.JwtRequestFilter;
import com.training.timekeeping.model.Overtime;
import com.training.timekeeping.service.OvertimeService;
import com.training.timekeeping.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/overtimes")
public class OvertimeController {

    @Autowired
    private OvertimeService service;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @GetMapping("/get-overtimes")
    public ResponseEntity<?> getOvertimes() {
        List<Overtime> overtimes = service.getOvertimes();
        return ResponseEntity.ok(overtimes);
    }

    @GetMapping("/get-overtimes-employee")
    public ResponseEntity<?> getOvertimesByEmployee(){
        List<Overtime> overtimes = service.getOvertimesByEmployee(jwtRequestFilter.getEmail());
        return ResponseEntity.ok(overtimes);
    }

    @GetMapping("/get-overtime")
    public ResponseEntity<?> getOvertime(@RequestParam(name = "email") String email,
                                            @RequestParam(name = "date") String date) {
        Overtime overtime = service.getOvertime(email, LocalDate.parse(date));
        return ResponseEntity.ok(overtime);
    }

    @PostMapping("/create-overtime")
    public ResponseEntity<?> createOvertime(@RequestBody Overtime overtime) {
        service.createOvertime(jwtRequestFilter.getEmail(), overtime);
        return ResponseEntity.ok(overtime);
    }

    @PutMapping("/edit-overtime")
    public ResponseEntity<?> editOvertime(@RequestBody Overtime overtime) {
        boolean b = service.editOvertime(jwtRequestFilter.getEmail(), overtime);
        if (b) {
            return ResponseEntity.ok(overtime);
        }
        return (ResponseEntity<?>) ResponseEntity.badRequest();
    }
}
