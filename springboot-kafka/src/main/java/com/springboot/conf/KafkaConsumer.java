package com.springboot.conf;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "mytopic")
    public void listene(ConsumerRecord<?, String> record) {
        String value = record.value();
        System.out.println(value + ":" + record);
    }
}
