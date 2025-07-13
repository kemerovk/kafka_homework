package me.project.kafka_practice.service;

import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;

import java.time.LocalDate;
import java.util.Random;

public class WeatherGenerator {

    private static final Random rand = new Random();
    private static final String[] cities = {"Санкт-Петербург", "Москва", "Пятигорск", "Рязань", "Калининград", "Владимир"};
    private static final LocalDate date = LocalDate.of(2024, 1, 1);
    private static final WeatherCondition[] weatherConditions = WeatherCondition.values();


    public static Weather generateWeather() {
        return new Weather(rand.nextInt(0, 41),
                weatherConditions[rand.nextInt(weatherConditions.length)],
                cities[rand.nextInt(cities.length)],
                date.plusDays(rand.nextInt(365)));
    }
}
