package com.blacksystems.app.hr.integrationtest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.blacksystems.app.pojo.Employee;

@IntegrationTest
public class EmployeeAppIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAppIntegrationTest.class);
    
    @SuppressWarnings("unchecked")
    @Test
    public void employeeList() {
        final String url = "http://localhost:9999/employeeapp/employee/list";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity requestEntity = new HttpEntity("", headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        LOGGER.debug(result.toString());
        Assert.assertEquals(200, result.getStatusCode().value());
        /*Assert
            .assertEquals(
                "[{\"id\":\"1\",\"name\":\"Taylor\",\"city\":\"Bangalore\",\"age\":\"45\",\"dob\":\"01-04-2017\"},{\"id\":\"2\",\"name\":\"Lan\",\"city\":\"Chicago\",\"age\":\"45\",\"dob\":\"01-11-2017\"}]",
                result.getBody());*/
    }

    @SuppressWarnings("unchecked")
    @Test
    public void employeeCreate() throws JsonParseException, JsonMappingException, IOException {
        final String url = "http://localhost:9999/employeeapp/employee/create";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity requestEntity = new HttpEntity(
            "{ \"name\":\"John\", \"age\":33, \"city\":\"New York\" , \"dob\":\"26/11/1987\"}", headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        LOGGER.debug(result.toString());
        Assert.assertEquals(200, result.getStatusCode().value());
        
        ObjectMapper mapper = new ObjectMapper();
        Employee emp = mapper.readValue(result.getBody(), Employee.class);
        Assert.assertNotNull(emp);
        Assert.assertNotNull(emp.getId());
       
    }

}
