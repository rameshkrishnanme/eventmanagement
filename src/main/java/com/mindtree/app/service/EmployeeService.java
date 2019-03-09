package com.mindtree.app.service;

import java.util.List;

import com.mindtree.app.pojo.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getList();

}
