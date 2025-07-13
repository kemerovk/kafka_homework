package me.project.kafka_practice.data;


import java.time.LocalDate;
import java.util.Objects;

public record Weather (
        int temperature,
        WeatherCondition condition,
        String city,
        LocalDate date){

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return date.isEqual(weather.date) && city.equals(weather.city());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, date);
    }
}
