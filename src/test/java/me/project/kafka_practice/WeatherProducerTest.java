package me.project.kafka_practice;


import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.data.WeatherCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = {"weather"})
class WeatherProducerTest {

    @Autowired
    private KafkaTemplate<String, Weather> kafkaTemplate;

    @Test
    void testSendMessageToKafkaTopic() throws ExecutionException, InterruptedException {
        Weather weather = new Weather(30, WeatherCondition.CLOUDY, "Санкт-Петербург", java.time.LocalDate.now());

        var sendResult = kafkaTemplate.send("weather", weather).get();

        assertThat(sendResult.getRecordMetadata().topic()).isEqualTo("weather");
        assertThat(sendResult.getRecordMetadata().partition()).isGreaterThanOrEqualTo(0);
    }
}