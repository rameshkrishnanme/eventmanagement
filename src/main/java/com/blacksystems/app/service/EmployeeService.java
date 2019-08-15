package com.blacksystems.app.service;

import java.util.List;

import com.blacksystems.app.pojo.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getList();

}
