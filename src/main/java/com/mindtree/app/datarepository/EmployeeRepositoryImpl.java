package com.mindtree.app.datarepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mindtree.app.entity.Employee;



@Service
public class EmployeeRepositoryImpl implements IEmployeeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    public EmployeeRepositoryImpl() {

    }

    @SuppressWarnings("unchecked")
    public List<com.mindtree.app.pojo.Employee> list() {
        LOGGER.debug("Database, Listing all employees");
        final Session newSession = HibernateUtil.createNewSession();
        try {
            newSession.beginTransaction();
            String hql = "FROM Employee";
            Query query = newSession.createQuery(hql);
            List<Employee> results = query.list();
            List<com.mindtree.app.pojo.Employee> employees = new ArrayList<com.mindtree.app.pojo.Employee>();

            for (Employee employee : results) {
                final com.mindtree.app.pojo.Employee employeeEntityToPojo = ConvertObjects
                    .employeeEntityToPojo(employee);
                employees.add(employeeEntityToPojo);
            }
            LOGGER.debug("Database, Listing all employees, Success");
            return employees;
        }
        finally {
            HibernateUtil.closeSession(newSession);
        }

    }

    public com.mindtree.app.pojo.Employee find(com.mindtree.app.pojo.Employee employee) {
        final Collection<com.mindtree.app.pojo.Employee> values = list();
        final List<com.mindtree.app.pojo.Employee> empList = new ArrayList<com.mindtree.app.pojo.Employee>(values);
        final int indexOf = empList.indexOf(employee);
        if (indexOf > -1) {
            return empList.get(indexOf);
        }
        return null;
    }

    public com.mindtree.app.pojo.Employee save(com.mindtree.app.pojo.Employee employee) {
        LOGGER.debug("Database, Creating new employee {}", employee);
        final Session newSession = HibernateUtil.createNewSession();
        try {

            newSession.beginTransaction();
            Employee employees = new Employee(null, employee.getMid(), employee.getName(), employee.getJoinDate(),
                employee.getEmail());
            newSession.save(employees);
            employee.setId(employees.getId());
            LOGGER.debug("Database, Creating new employee {}, Success", employee);
            return employee;
        }
        finally {
            HibernateUtil.closeSession(newSession);
        }
    }
  

}
