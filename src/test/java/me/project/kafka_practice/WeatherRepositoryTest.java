package me.project.kafka_practice;

import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository repository;

    private Weather testWeather;

    @BeforeEach
    void setUp() {
        testWeather = new Weather(20, WeatherCondition.SUNNY, "Москва", LocalDate.now());
        repository.add(testWeather);
    }

    @Test
    void testAdd_weatherIsAdded() {
        assertTrue(repository.contains(testWeather));
    }

    @Test
    void testContains_returnsFalseForUnknownWeather() {
        Weather another = new Weather(25, WeatherCondition.CLOUDY, "Санкт-Петербург", LocalDate.now());
        assertFalse(repository.contains(another));
    }
}
