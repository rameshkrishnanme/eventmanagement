package com.blacksystems.app.datarepository;

import com.blacksystems.app.entity.Event;

public class ConvertObjects {

    public static com.blacksystems.app.pojo.Employee employeeEntityToPojo(com.blacksystems.app.entity.Employee employee) {
        com.blacksystems.app.pojo.Employee employeePojo = new com.blacksystems.app.pojo.Employee();
        employeePojo.setEmail(employee.getEmail());
        employeePojo.setId(employee.getId());
        employeePojo.setJoinDate(employee.getJoinDate());
        employeePojo.setMid(employee.getMid());
        employeePojo.setName(employee.getName());
        return employeePojo;
    }

    public static com.blacksystems.app.pojo.Event eventEntityToPojo(Event event) {
        com.blacksystems.app.pojo.Event event2 = new com.blacksystems.app.pojo.Event();
        event2.setEventId(event.getId());
        event2.setEventTitle(event.getEventTitle());
        event2.setEventDesc(event.getDescription());
        return event2;
    }
}
