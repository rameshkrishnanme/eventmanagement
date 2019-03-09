package com.mindtree.app.datarepository;

import java.util.List;

import com.mindtree.app.pojo.Event;
import com.mindtree.app.pojo.EventRegistration;

public interface IEventManagementDR {

    public EventRegistration registerEmployee(EventRegistration eventRegistration);

    EventRegistration registeredEmployeeByEventId(EventRegistration eventRegistration);

    List<Event> getEvents();

}
