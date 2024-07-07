package com.study.kafka_practice;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProduceService {

    private static final String TOPIC_NAME = "topic5";
    private final KafkaTemplate<String, String> kafkaTemplate;


    public void send(String message) {
        kafkaTemplate.send(TOPIC_NAME,message);
    }

    public void sentWithCallback(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME,message);

        future.thenAccept(SendResult ->{
            System.out.println("sent : " + message+ " offset" +SendResult.getRecordMetadata().offset());
        }).exceptionally(error -> {
            System.out.println("failed to send : " + message+ " due to" +error.getMessage());
            return null;
        });
    }
}
