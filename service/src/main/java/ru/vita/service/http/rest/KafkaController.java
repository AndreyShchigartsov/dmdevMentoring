package ru.vita.service.http.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vita.service.config.KafkaProducerConfig;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducerConfig kafkaProducerConfig;

    public KafkaController(KafkaProducerConfig kafkaProducerConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String topic, @RequestParam String message) {
        kafkaProducerConfig.sendMessage(topic, message);
        return "Message send to topic " + topic;
    }
}
