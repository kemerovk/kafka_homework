package me.project.kafka_practice.kafka;

import lombok.extern.slf4j.Slf4j;
import me.project.kafka_practice.data.Weather;
import me.project.kafka_practice.service.WeatherAnalyzerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherConsumer {

    @Autowired
    private WeatherAnalyzerService service;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "weather-group")
    public void listen(ConsumerRecord<String, Weather> record) {
        log.info("Received Weather data: {}", record.value());
        service.addWeather(record.value());
        if (service.sizeOfDataByCity(record.value().city()) % 10 == 0) {
            service.analyze(record.value().city());
        }
    }

}