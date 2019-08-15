package com.blacksystems.app.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import com.blacksystems.app.datarepository.IEventManagementDR;
import com.blacksystems.app.exception.InsufficientDataException;
import com.blacksystems.app.pojo.EventRegistration;

@Service
@Validated
public class EventManagementServiceImpl implements EventManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManagementServiceImpl.class);
    private final IEventManagementDR iEventManagementDR;

    @Inject
    public EventManagementServiceImpl(final IEventManagementDR repository) {
        this.iEventManagementDR = repository;
    }
    

    @Override
    public List<com.blacksystems.app.pojo.Event> getEventList() {
        LOGGER.debug("Retrieving the list of Event");
        return iEventManagementDR.getEvents();
    }

    @Override
    public EventRegistration registerEmpForEvent(EventRegistration evtReg) {
        LOGGER.debug("Registrating a employee for event {}", evtReg);
        if (!haveSufficientData(evtReg)) {
            throw new InsufficientDataException(
                String.format("Please provide all data to register a employee for event"));
        }
        return iEventManagementDR.registerEmployee(evtReg);
    }
    
    @Override
    public EventRegistration registeredEmpsForEvent(EventRegistration evtReg) {
        LOGGER.debug("Registrating a employee for event {}", evtReg);
        if (!haveSufficientDataForGetER(evtReg)) {
            throw new InsufficientDataException(
                String.format("Please provide all data to get registered employees for an event"));
        }
        return iEventManagementDR.registeredEmployeeByEventId(evtReg);
    }

    private boolean haveSufficientDataForGetER(EventRegistration evtReg) {
        if (evtReg == null || StringUtils.isEmpty(evtReg.getEventId())) {
            return false;
        }
        return true;
    }

    
    private boolean haveSufficientData(EventRegistration evtReg) {
        if (evtReg == null || StringUtils.isEmpty(evtReg.getEventId()) || StringUtils.isEmpty(evtReg.getEmployeeId())) {
            return false;
        }
        return true;
    }

}
