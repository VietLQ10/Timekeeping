package com.training.timekeeping.service.impl;

import com.training.timekeeping.model.Department;
import com.training.timekeeping.model.Gender;
import com.training.timekeeping.model.Position;
import com.training.timekeeping.model.dto.Account;
import com.training.timekeeping.model.dto.EmployeeDTO;
import com.training.timekeeping.utils.Constant;
import com.training.timekeeping.model.Employee;
import com.training.timekeeping.repository.EmployeeRepository;
import com.training.timekeeping.service.EmployeeService;
import com.training.timekeeping.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
    * create
    *
    * */
    // create admin
    @Override
    public void createAdmin() {
        Employee emp = new Employee();
        emp.setEmployeeId("1");
        emp.setDepartment(new Department("BU2"));
        emp.setPosition(new Position("DEV"));
        emp.setGender(new Gender(1));
        emp.setName("La Quoc Viet");
        emp.setEmail("vietlq@ominext.com");
        emp.setPassword(bcryptEncoder.encode("vietlq@ominext.com"));
        emp.setNumhoursoff(30);
        emp.setNumRemaining(30);
        emp.setTimeStartWork(LocalTime.of(8, 15));
        emp.setTimeBreak(LocalTime.of(11, 45));
        emp.setTimeEndWork(LocalTime.of(17, 30));
        emp.setRole(Constant.ROLE_ADMIN);
        repository.save(emp);
    }

    // create user
    @Override
    public boolean createUser(String email, Employee employee) {
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(getEmployee(Constant.EMPLOYEE_EMAIL, email).getRole())) {
            employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
            repository.save(employee);
            return true;
        }
        return false;
    }

    /**
    * retrieve
    *
    * */

    // get all employees
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        repository.findAll().forEach(employee -> {
            employeeDTOS.add(new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getDepartment().getDepartmentName(),
                    employee.getPosition().getPositionName(),
                    employee.getGender().getGender(),
                    employee.getNumhoursoff(),
                    employee.getNumRemaining()
            ));
        });
        return employeeDTOS;
    }

    // get list employees
    @Override
    public List<EmployeeDTO> getEmployeesDTO(String type, Object data) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_NAME:
                repository.findByName((String) data).forEach(employee -> {
                    employeeDTOS.add(new EmployeeDTO(
                            employee.getEmployeeId(),
                            employee.getName(),
                            employee.getEmail(),
                            employee.getDepartment().getDepartmentName(),
                            employee.getPosition().getPositionName(),
                            employee.getGender().getGender(),
                            employee.getNumhoursoff(),
                            employee.getNumRemaining()
                    ));
                });
                break;

        }
        return employeeDTOS;
    }

    @Override
    public List<Employee> getEmployees(String type, Object data) {
        List<Employee> employees = new ArrayList<>();
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_NAME:
                repository.findByName((String) data).forEach(employee -> {
                    employees.add(employee);
                });
                break;
        }
        return employees;
    }

    public Employee getEmployee(String type, Object data) {
        Optional<Employee> optional = null;
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_ID:
                optional = repository.findById((String) data);
                break;

            case Constant.EMPLOYEE_EMAIL:
                optional = repository.findByEmail((String) data);
                break;
        }

        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    // get a employee
    @Override
    public EmployeeDTO getEmployeeDTO(String type, Object data) {
        Optional<Employee> optional = null;
        switch (type.toUpperCase()) {
            case Constant.EMPLOYEE_ID:
                optional = repository.findById((String) data);
                break;

            case Constant.EMPLOYEE_EMAIL:
                optional = repository.findByEmail((String) data);
                break;
        }

        if (optional.isPresent()) {
            Employee employee = optional.get();
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getDepartment().getDepartmentName(),
                    employee.getPosition().getPositionName(),
                    employee.getGender().getGender(),
                    employee.getNumhoursoff(),
                    employee.getNumRemaining()
            );
            return employeeDTO;
        }
        return null;
    }

    // override interface UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> optional = repository.findByEmail(email);
        if (! optional.isPresent()) {
            throw new UsernameNotFoundException("Email not found with email: " + email);
        }
        return new User(optional.get().getEmail(), optional.get().getPassword(), new ArrayList<>());
    }


    /**
    * update
    *
    * */
    // update employee:
    @Override
    public boolean updateEmployee(String email, Employee emp) {
        Employee employee = getEmployee(Constant.EMPLOYEE_EMAIL, email);
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
            emp.setPassword(bcryptEncoder.encode(emp.getPassword()));
            repository.save(emp);
            return true;
        } else {
            employee.setPassword(bcryptEncoder.encode(emp.getPassword()));
            employee.setTimeStartWork(emp.getTimeStartWork());
            employee.setTimeEndWork(emp.getTimeEndWork());
            employee.setTimeBreak(emp.getTimeBreak());
            return true;
        }
//        Employee employee = getEmployee(Constant.EMPLOYEE_EMAIL, email);
//        String role = employee.getRole();
//
//        if (emp == null) {
//            return false;
//        }
//
//        if (getEmployee(Constant.EMPLOYEE_ID, emp.getEmployeeId()) == null){
//            return false;
//        }
//
//        if (Constant.ROLE_ADMIN.equalsIgnoreCase(role)) {
//            emp.setPassword(bcryptEncoder.encode(emp.getPassword()));
//            repository.save(emp);
//            return true;
//
//        } else {
//            if (! email.equalsIgnoreCase(emp.getEmail())) {
//                return false;
//            }
//
//            employee.setPassword(bcryptEncoder.encode(emp.getPassword()));
//            employee.setTimeStartWork(emp.getTimeStartWork());
//            employee.setTimeEndWork(emp.getTimeEndWork());
//            employee.setTimeBreak(emp.getTimeBreak());
//
//            return true;
//        }
    }


    /**
    * delete
    *
    * */
    // delete list employees
    @Override
    public boolean deleteEmployees(String email, Iterable<Employee> employees) {
        Employee employee = getEmployee(Constant.EMPLOYEE_EMAIL, email);
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
            repository.deleteAll(employees);
            return true;
        }
        return false;
    }

    // delete a employee
    @Override
    public boolean deleteEmployee(String email, Employee emp) {
        Employee employee = getEmployee(Constant.EMPLOYEE_EMAIL, email);
        if (Constant.ROLE_ADMIN.equalsIgnoreCase(employee.getRole())) {
            repository.delete(emp);
            return true;
        }
        return false;
    }

}
