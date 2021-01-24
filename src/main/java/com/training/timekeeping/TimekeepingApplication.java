package com.training.timekeeping;

import com.training.timekeeping.model.Employee;
import com.training.timekeeping.repository.EmployeeRepository;
import com.training.timekeeping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;

@SpringBootApplication
public class TimekeepingApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TimekeepingApplication.class, args);
        EmployeeService service = context.getBean(EmployeeService.class);
        service.createAdmin();

    }

}
