package me.project.kafka_practice;

import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.repository.WeatherRepository;
import me.project.kafka_practice.service.WeatherAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherAnalyzerServiceTest {

    private WeatherAnalyzerService service;
    private WeatherRepository mockRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockRepo = mock(WeatherRepository.class);
        service = new WeatherAnalyzerService();
        service.setWeatherRepository(mockRepo);
    }

    @Test
    void testSizeOfDataByCity_returnsCorrectValue() {
        when(mockRepo.getWeatherSet()).thenReturn(Set.of(
                new Weather(20, WeatherCondition.SUNNY, "Москва", LocalDate.now()),
                new Weather(18, WeatherCondition.RAINY, "Москва", LocalDate.now().minusDays(10))
        ));

        assertEquals(2, service.sizeOfDataByCity("Москва"));
    }
}