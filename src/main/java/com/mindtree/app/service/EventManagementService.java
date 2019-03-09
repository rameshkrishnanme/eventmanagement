package com.mindtree.app.service;

import java.util.List;

import com.mindtree.app.pojo.Event;
import com.mindtree.app.pojo.EventRegistration;

public interface EventManagementService {
    
    EventRegistration registerEmpForEvent(EventRegistration evtReg);

    EventRegistration registeredEmpsForEvent(EventRegistration evtReg);

    List<Event> getEventList();


}
