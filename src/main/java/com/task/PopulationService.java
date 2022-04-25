package com.task;

import com.task.dto.Population;

import static io.restassured.RestAssured.given;

public class PopulationService {
    public static final String POPULATION_ENDPOINT
            = "https://datausa.io/api/data";

    public Population getPopulation(String drilldowns, String measures, String year) {
        return given()
                .baseUri(POPULATION_ENDPOINT)
                .when()
                .queryParam("drilldowns", drilldowns)
                .queryParam("measures", measures)
                .queryParam("year", year)
                .get()
                .getBody().as(Population.class);
    }
}
