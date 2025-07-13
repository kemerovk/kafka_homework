package me.project.kafka_practice.kafka;

import lombok.extern.slf4j.Slf4j;
import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.service.WeatherGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherProducer {

    private final String topic;
    private final KafkaTemplate<String, Weather> kafkaTemplate;

    @Autowired
    public WeatherProducer(@Value("${spring.kafka.template.default-topic}") String topic,
                           KafkaTemplate<String, Weather> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void generateAndSend() {
        Weather weather = WeatherGenerator.generateWeather();
        kafkaTemplate.send(topic, weather);
        log.info("Sent weather to topic {}", topic);

    }
}