package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.model.Request;
import com.training.timekeeping.model.dto.RequestDTO;
import com.training.timekeeping.repository.RequestRepository;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.service.RequestService;
import com.training.timekeeping.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private EmployeeService service;

    @Override
    public boolean createRequest(Request request) {
        repository.save(request);
        return true;
    }

    @Override
    public List<RequestDTO> findAll(String employeeEmail) {
        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, employeeEmail);
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
            List<RequestDTO> requests = new ArrayList<>();
            repository.findAll().forEach(request -> {
                RequestDTO requestDTO = new RequestDTO(request.getRequestId(),
                        request.getTimeStart(),
                        request.getTimeEnd(),
                        request.getContent(),
                        request.getEmployee().getName());
                requests.add(requestDTO);
            });
            return requests;

        } else {
            List<RequestDTO> requests = new ArrayList<>();
            repository.findByEmployeeId(employeeEmail).forEach(request -> {
                RequestDTO requestDTO = new RequestDTO(request.getRequestId(),
                        request.getTimeStart(),
                        request.getTimeEnd(),
                        request.getContent(),
                        request.getEmployee().getName());
                requests.add(requestDTO);
            });
            return requests;
        }
    }

    @Override
    public RequestDTO viewRequest(String employeeEmail, int requestId) {
//        Employee employee = service.getEmployee(Constant.EMPLOYEE_EMAIL, employeeEmail);
//        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
//
//        } else {
//            List<Request> result = repository.findByEmployeeId(employeeEmail);
//            for (Request request : result) {
//                if (requestId == request.getRequestId()) {
//                    return request;
//                }
//            }
//        }
        List<RequestDTO> requests = findAll(employeeEmail);
        for (RequestDTO request : requests) {
            if (requestId == request.getRequestId()) {
                return request;
            }
        }
        return null;
    }

}
