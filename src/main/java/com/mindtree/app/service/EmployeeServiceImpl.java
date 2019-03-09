package com.mindtree.app.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.mindtree.app.datarepository.IEmployeeRepository;
import com.mindtree.app.exception.DuplicateDataException;
import com.mindtree.app.exception.InsufficientDataException;
import com.mindtree.app.pojo.Employee;

@Service
@Validated
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final IEmployeeRepository eRepository;

    @Inject
    public EmployeeServiceImpl(final IEmployeeRepository repository) {
        this.eRepository = repository;
    }

    @Override
    public Employee save(final Employee employee) {
        LOGGER.debug("Creating {}", employee);
        Employee existing = eRepository.find(employee);
        if (existing != null) {
            throw new DuplicateDataException(String.format("There already exists a employee with same details"));
        }
        if (!haveSufficientData(employee)) {
            throw new InsufficientDataException(String.format("Please provide all data to creare employee"));
        }
        return eRepository.save(employee);
    }

    private boolean haveSufficientData(Employee employee) {
        if (employee == null 
            || StringUtils.isEmpty(employee.getEmail()) 
            || StringUtils.isEmpty(employee.getJoinDate())
            || StringUtils.isEmpty(employee.getMid()) 
            || StringUtils.isEmpty(employee.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public List<Employee> getList() {
        LOGGER.debug("Retrieving the list of all employees");
        return eRepository.list();
    }

}
