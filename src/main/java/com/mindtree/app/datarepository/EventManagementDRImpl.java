package com.mindtree.app.datarepository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mindtree.app.entity.Employee;
import com.mindtree.app.entity.Event;
import com.mindtree.app.entity.EventRegistration;

@Service
public class EventManagementDRImpl implements IEventManagementDR {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventManagementDRImpl.class);

    public EventManagementDRImpl() {

    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<com.mindtree.app.pojo.Event> getEvents() {
        LOGGER.debug("Database, Listing all events");
        final Session newSession = HibernateUtil.createNewSession();
        try {

            newSession.beginTransaction();
            String hql = "FROM Event";
            Query query = newSession.createQuery(hql);
            List<Event> results = query.list();
            List<com.mindtree.app.pojo.Event> events = new ArrayList<com.mindtree.app.pojo.Event>();

            for (Event eventEnt : results) {
                final com.mindtree.app.pojo.Event employeeEntityToPojo = ConvertObjects.eventEntityToPojo(eventEnt);
                events.add(employeeEntityToPojo);
            }
            LOGGER.debug("Database, Listing all employees, Success");
            return events;
        }
        finally {
            HibernateUtil.closeSession(newSession);
        }
    }
    
    @Override
    public com.mindtree.app.pojo.EventRegistration registerEmployee(
        com.mindtree.app.pojo.EventRegistration eventRegistration) {
        LOGGER.debug("Database, Registering employee for event", eventRegistration);
        final Session newSession = HibernateUtil.createNewSession();
        try {
            newSession.beginTransaction();
            com.mindtree.app.entity.EventRegistration eventRegistrationEntity = new com.mindtree.app.entity.EventRegistration();
            eventRegistrationEntity.setEmployeeId(eventRegistration.getEmployeeId());
            eventRegistrationEntity.setEventId(eventRegistration.getEventId());
    
            newSession.save(eventRegistrationEntity);
            LOGGER.debug("Database, Registering employee for event, Success", eventRegistration);
            eventRegistration.setStatus(Boolean.TRUE);
        }
        finally {
            HibernateUtil.closeSession(newSession);
        }
        return eventRegistration;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public com.mindtree.app.pojo.EventRegistration registeredEmployeeByEventId(
        com.mindtree.app.pojo.EventRegistration eventRegistration) {
        LOGGER.debug("Database, Searching registered employee for a event", eventRegistration);
        final Session newSession = HibernateUtil.createNewSession();
        try {
            newSession.beginTransaction();

            final Query createQuery = newSession.createQuery(" FROM EventRegistration where eventId = :evtID ");
            createQuery.setParameter("evtID", eventRegistration.getEventId());
            final List list = createQuery.list();
            final List<EventRegistration> eventRegistrationList = list;

            if (eventRegistrationList.size() > 0) {

                LOGGER.debug("Database, Results {} ", eventRegistrationList);

                for (EventRegistration eventRegistrationEnts : eventRegistrationList) {
                    final Employee employee = eventRegistrationEnts.getEmployees();
                    eventRegistration.getEmployeeList().add(ConvertObjects.employeeEntityToPojo(employee));
                }

                LOGGER.debug("Database, Created Employee Object", eventRegistration.getEmployeeList());

                final Event events = eventRegistrationList.get(0).getEvents();
                com.mindtree.app.pojo.Event event = new com.mindtree.app.pojo.Event();
                eventRegistration.setEvent(event);
                event.setEventId(events.getId());
                event.setEventTitle(events.getEventTitle());
                eventRegistration.setStatus(Boolean.TRUE);
            }
            else {
                eventRegistration.setStatus(Boolean.FALSE);
            }

            LOGGER.debug("Database, Searching registered employee for a event", eventRegistration);
        }
        finally {
            HibernateUtil.closeSession(newSession);
        }
        return eventRegistration;
    }

}
