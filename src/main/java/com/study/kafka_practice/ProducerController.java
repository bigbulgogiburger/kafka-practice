package com.study.kafka_practice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaProduceService kafkaProduceService;

    @RequestMapping("/publish")
    public String publish(String message) {
        kafkaProduceService.send(message);
        return "published a message : "+message;
    }


    @RequestMapping("/publish2")
    public String publish2(String message) {
        kafkaProduceService.sentWithCallback(message);
        return "published a message with callback : "+message;
    }
}
