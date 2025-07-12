package me.project.kafka_practice.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WeatherConsumer {


    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void listen(String message) {
        System.out.println(message);
    }
}
