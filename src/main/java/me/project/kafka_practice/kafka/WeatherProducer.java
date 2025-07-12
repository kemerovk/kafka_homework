package me.project.kafka_practice.kafka;

import me.project.kafka_practice.data.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {

    private final KafkaTemplate<String, Weather> producer;

    public WeatherProducer(KafkaTemplate<String, Weather> producer) {
        this.producer = producer;
    }


    public void send(@Value("${spring.kafka.template.default-topic}") String topic, Weather weather) {
        System.out.println("sent weather to topic: " + topic);
        producer.send(topic, weather);
    }
}
