package com.training.timekeeping.controller;

import com.training.timekeeping.service.OvertimeService;
import com.training.timekeeping.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/overtimes")
public class OvertimeController {

    @Autowired
    private OvertimeService service;

    @GetMapping("/get-overtimes")
    public ResponseEntity<?> getOvertimes(@RequestParam(name = "id") String employeeId,
                                            @RequestParam(name = "date") String date) {
        long timeCheckOut = service.getOvertime(employeeId, LocalDate.parse(date));
        return ResponseEntity.ok(Util.convertTime(timeCheckOut));
    }
}
