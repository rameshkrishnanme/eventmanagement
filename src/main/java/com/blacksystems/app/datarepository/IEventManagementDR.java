package com.blacksystems.app.datarepository;

import java.util.List;

import com.blacksystems.app.pojo.Event;
import com.blacksystems.app.pojo.EventRegistration;

public interface IEventManagementDR {

    public EventRegistration registerEmployee(EventRegistration eventRegistration);

    EventRegistration registeredEmployeeByEventId(EventRegistration eventRegistration);

    List<Event> getEvents();

}
