package com.blacksystems.app.service;

import java.util.List;

import com.blacksystems.app.pojo.Event;
import com.blacksystems.app.pojo.EventRegistration;

public interface EventManagementService {
    
    EventRegistration registerEmpForEvent(EventRegistration evtReg);

    EventRegistration registeredEmpsForEvent(EventRegistration evtReg);

    List<Event> getEventList();


}
