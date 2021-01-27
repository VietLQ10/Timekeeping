package com.training.timekeeping.service;

import com.training.timekeeping.model.Request;
import com.training.timekeeping.model.dto.RequestDTO;

import java.util.List;

public interface RequestService {

    // create request
    boolean createRequest(Request request);

    // get all requests
    List<RequestDTO> findAll(String employeeEmail);

    // view request
    RequestDTO viewRequest(String employeeEmail, int requestId);


}
