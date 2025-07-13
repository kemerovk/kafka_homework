package me.project.kafka_practice;

import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import me.project.kafka_practice.kafka.WeatherConsumer;
import me.project.kafka_practice.service.WeatherAnalyzerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class WeatherConsumerTest {

    @InjectMocks
    private WeatherConsumer weatherConsumer;

    @Mock
    private WeatherAnalyzerService weatherAnalyzerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOnMessageReceived_ShouldCallAddAndAnalyze() {
        Weather weather = new Weather(25, WeatherCondition.SUNNY, "Moscow", LocalDate.now());

        weatherConsumer.listen(new ConsumerRecord<>("weather", 0, 0L, "key", weather));

        verify(weatherAnalyzerService, times(1)).addWeather(weather);
        verify(weatherAnalyzerService, atMostOnce()).analyze("Moscow");
    }
}