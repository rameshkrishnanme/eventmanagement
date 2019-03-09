package com.mindtree.app.datarepository;

import java.util.List;

import com.mindtree.app.pojo.Employee;

public interface IEmployeeRepository {
    
    public List<Employee> list();

    public Employee find(Employee employee);

    public Employee save(Employee employee);
    
}
