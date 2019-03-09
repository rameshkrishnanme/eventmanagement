package com.mindtree.app.datarepository;

import com.mindtree.app.entity.Event;

public class ConvertObjects {

    public static com.mindtree.app.pojo.Employee employeeEntityToPojo(com.mindtree.app.entity.Employee employee) {
        com.mindtree.app.pojo.Employee employeePojo = new com.mindtree.app.pojo.Employee();
        employeePojo.setEmail(employee.getEmail());
        employeePojo.setId(employee.getId());
        employeePojo.setJoinDate(employee.getJoinDate());
        employeePojo.setMid(employee.getMid());
        employeePojo.setName(employee.getName());
        return employeePojo;
    }

    public static com.mindtree.app.pojo.Event eventEntityToPojo(Event event) {
        com.mindtree.app.pojo.Event event2 = new com.mindtree.app.pojo.Event();
        event2.setEventId(event.getId());
        event2.setEventTitle(event.getEventTitle());
        event2.setEventDesc(event.getDescription());
        return event2;
    }
}
