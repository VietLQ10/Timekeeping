package com.training.timekeeping.controller;

import com.training.timekeeping.config.JwtRequestFilter;
import com.training.timekeeping.model.Request;
import com.training.timekeeping.model.dto.RequestDTO;
import com.training.timekeeping.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService service;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @GetMapping("/find-all-requests")
    public ResponseEntity<?> findAllRequest(){
        List<RequestDTO> requests = service.findAll(jwtRequestFilter.getEmail());
        return ResponseEntity.ok(requests);
    }

    @PostMapping("/create-request")
    public ResponseEntity<?> createRequest(@RequestBody Request request){
        boolean isCreate = service.createRequest(request);
        if (isCreate) {
            return ResponseEntity.ok(request);
        } else {
            return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/view-request")
    public ResponseEntity<?> viewRequest(@RequestParam (name = "request") Integer requestId){
        RequestDTO request = service.viewRequest(jwtRequestFilter.getEmail(), requestId);
        return ResponseEntity.ok(request);
    }
}
