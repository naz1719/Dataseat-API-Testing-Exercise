package com.task;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopulationTest {

    private final PopulationService populationService = new PopulationService();

    @Test
    public void testFailTestRunStatusUpdate() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        populationService.getPopulation("Nation", "Population", "2013");

//        assertEquals("Status of issue should be FAIL", expectedStatus.name(), testRunDto.getStatus());
    }
}
