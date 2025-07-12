package me.project.kafka_practice.data;


import java.time.LocalDate;

public record Weather (int temperature,
                       WeatherCondition condition,
                       String city,
                       LocalDate date){}
