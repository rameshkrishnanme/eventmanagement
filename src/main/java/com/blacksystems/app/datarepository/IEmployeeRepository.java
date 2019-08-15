package com.blacksystems.app.datarepository;

import java.util.List;

import com.blacksystems.app.pojo.Employee;

public interface IEmployeeRepository {
    
    public List<Employee> list();

    public Employee find(Employee employee);

    public Employee save(Employee employee);
    
}
