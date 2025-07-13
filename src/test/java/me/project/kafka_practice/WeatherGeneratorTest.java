package me.project.kafka_practice;



import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.service.WeatherGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherGeneratorTest {

    @Test
    void testGeneratedWeather_hasValidTemperature() {
        Weather weather = WeatherGenerator.generateWeather();
        int temperature = weather.temperature();
        assertTrue(temperature >= 0 && temperature <= 40);
    }

    @Test
    void testGeneratedWeather_hasValidCity() {
        Weather weather = WeatherGenerator.generateWeather();
        assertTrue(java.util.Arrays.asList("Санкт-Петербург", "Москва", "Пятигорск", "Рязань", "Калининград", "Владимир")
                .contains(weather.city()));
    }

    @Test
    void testGeneratedWeather_hasValidCondition() {
        Weather weather = WeatherGenerator.generateWeather();
        assertNotNull(weather.condition());
        assertTrue(java.util.Arrays.stream(WeatherCondition.values())
                .anyMatch(cond -> cond == weather.condition()));
    }

    @Test
    void testGeneratedWeather_hasValidDateIn2024() {
        Weather weather = WeatherGenerator.generateWeather();
        LocalDate date = weather.date();
        assertTrue(date.isAfter(LocalDate.of(2023, 12, 31)) &&
                date.isBefore(LocalDate.of(2025, 1, 1)));
    }
}
