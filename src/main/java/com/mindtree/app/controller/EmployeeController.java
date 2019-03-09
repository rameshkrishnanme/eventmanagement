package com.mindtree.app.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.app.exception.DuplicateDataException;
import com.mindtree.app.exception.InsufficientDataException;
import com.mindtree.app.pojo.Employee;
import com.mindtree.app.service.EmployeeService;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Inject
    public EmployeeController(final EmployeeService EmployeeService) {
        this.employeeService = EmployeeService;
    }

    @RequestMapping(value = "/employee/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public Employee createEmployee(@RequestBody final Employee Employee) {
        LOGGER.debug("Received request to create the {}", Employee);
        return employeeService.save(Employee);
    }

    @RequestMapping(value = "/employee/list", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> listEmployees() {
        LOGGER.debug("Received request to list all Employees");
        return employeeService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateEmployeeException(DuplicateDataException e) {
        return e.getMessage();
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleInsuffcientEmployeeException(InsufficientDataException e) {
        return e.getMessage();
    }

}
