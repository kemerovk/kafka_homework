package me.project.kafka_practice.repository;

import lombok.Getter;
import me.project.kafka_practice.data.Weather;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class WeatherRepository {
    private final Set<Weather> weatherSet = ConcurrentHashMap.newKeySet();

    public void add(Weather weather) {
        weatherSet.add(weather);
    }

    public boolean contains(Weather weather) {
        return weatherSet.contains(weather);
    }

}
