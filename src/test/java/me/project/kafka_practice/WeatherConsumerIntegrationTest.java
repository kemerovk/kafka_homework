package me.project.kafka_practice;

import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.kafka.WeatherConsumer;
import me.project.kafka_practice.service.WeatherAnalyzerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class WeatherConsumerIntegrationTest {

    @Mock
    private WeatherAnalyzerService weatherAnalyzerService;

    @InjectMocks
    private WeatherConsumer weatherConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testWeatherMessageIsConsumed() {
        Weather testWeather = new Weather(20, WeatherCondition.SUNNY, "Moscow", LocalDate.now());

        weatherConsumer.listen(new ConsumerRecord<>("weather", 0, 0L, "key", testWeather));

        verify(weatherAnalyzerService, times(1)).addWeather(testWeather);
        verify(weatherAnalyzerService, atMostOnce()).analyze("Moscow");
    }
}