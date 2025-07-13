package me.project.kafka_practice.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j
@Component
public class WeatherAnalyzerService {
    @Autowired
    private WeatherRepository weatherRepository;

    public void addWeather(Weather weather){
        weatherRepository.add(weather);
    }


    public long sizeOfDataByCity(String city){
        return getWeatherByCity(city).size();
    }

    public Set<Weather> getWeatherByCity(String city){
        return weatherRepository
                .getWeatherSet()
                .stream()
                .filter(weather -> city.equals(weather.city()))
                .collect(Collectors.toSet());
    }


    public void analyze(String city) {

        Set<Weather> dataList = getWeatherByCity(city);
        double avgTemp = dataList
                .stream()
                .mapToInt(Weather::temperature)
                .average()
                .orElse(0);

        long rainyDays = dataList
                .stream()
                .filter(w -> w.condition() == WeatherCondition.RAINY)
                .count();

        long sunnyDays = dataList
                .stream()
                .filter(w -> w.condition() == WeatherCondition.SUNNY)
                .count();

        long cloudyDays = dataList
                .stream()
                .filter(w -> w.condition() == WeatherCondition.CLOUDY)
                .count();

        log.info("\nАналитика по городу {}:", city);
        log.info("Средняя температура: {}", String.format("%.1f", avgTemp));
        log.info("Дождливых дней: {}", rainyDays);
        log.info("Солнечных дней: {}", sunnyDays);
        log.info("Облачных дней: {}", cloudyDays);
    }

}
