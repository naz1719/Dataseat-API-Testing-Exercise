package com.task;

import com.task.dto.Datum;
import com.task.dto.Population;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PopulationTest {

    private final PopulationService populationService = new PopulationService();

    static {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @ParameterizedTest
    @ValueSource(ints = {2013, 2019})
    public void testPopulationInRange(Integer year) {
        Population population = populationService.getPopulation("Nation", "Population", year);
        int dataSize = population.getData().size();
        assertThat(0).isNotEqualTo(dataSize);
    }
    @ParameterizedTest
    @ValueSource(ints = {2012, 2020})
    public void testPopulationOutOfRange(Integer year) {
        Population population = populationService.getPopulation("Nation", "Population", year);
        int dataSize = population.getData().size();
        assertThat(0).isEqualTo(dataSize);
    }

    @Test
    public void testContract() {
        int year = 2013;
        Population population = populationService.getPopulation("Nation", "Population", year);
        Datum datum = population.getData().get(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(datum.getIDYear()).isEqualTo(year);
        softly.assertThat(datum.getYear()).isEqualTo(String.valueOf(year));
        softly.assertThat(datum.getIDNation()).isEqualTo("01000US");
        softly.assertThat(datum.getNation()).isEqualTo("United States");
        softly.assertThat(datum.getSlugNation()).isEqualTo("united-states");
        softly.assertAll();
    }
}
