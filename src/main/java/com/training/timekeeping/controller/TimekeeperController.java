package com.training.timekeeping.controller;

import com.training.timekeeping.model.Timekeeper;
import com.training.timekeeping.model.dto.TimekeeperDTO;
import com.training.timekeeping.service.TimekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/timekeepers")
public class TimekeeperController {

    @Autowired
    private TimekeeperService service;

    @GetMapping("/get-all-timekeeper")
    public ResponseEntity<?> getAll() {
        List<Timekeeper> timekeepers = service.getTimekeeper();
        List<TimekeeperDTO> timekeeperDTOs = new ArrayList<>();
        timekeepers.forEach(timekeeper -> {
            timekeeperDTOs.add(new TimekeeperDTO(timekeeper.getTimekeeperId().getEmployee().getName(),
                    timekeeper.getTimekeeperId().getTimeCheck(),
                    timekeeper.isCheckIn(),
                    timekeeper.isCheckOut()));
        });

        return ResponseEntity.ok(timekeepers);
    }
}
