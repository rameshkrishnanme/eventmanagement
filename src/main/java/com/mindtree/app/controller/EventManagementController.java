package com.mindtree.app.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.app.exception.DuplicateDataException;
import com.mindtree.app.exception.InsufficientDataException;
import com.mindtree.app.pojo.Event;
import com.mindtree.app.pojo.EventRegistration;
import com.mindtree.app.service.EventManagementService;

@RestController
public class EventManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManagementController.class);
    private final EventManagementService eventManagementService;

    @Inject
    public EventManagementController(final EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }

    @RequestMapping(value = "/event/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EventRegistration registerEmployee(@RequestBody final EventRegistration eventRegistration) {
        LOGGER.debug("Received request to Register for a event {}", eventRegistration);
        return eventManagementService.registerEmpForEvent(eventRegistration);
    }
    
    @RequestMapping(value = "/event/registered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EventRegistration registeredEmployee(@RequestBody final EventRegistration eventRegistration) {
        LOGGER.debug("Received request to get Registered employees", eventRegistration);
        return eventManagementService.registeredEmpsForEvent(eventRegistration);
    }
    
    @RequestMapping(value = "/event/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> events() {
        LOGGER.debug("Received request to get all events");
        return eventManagementService.getEventList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateEmployeeException(DuplicateDataException e) {
        return e.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleInsuffcientEmployeeException(InsufficientDataException e) {
        return e.getMessage();
    }

}
