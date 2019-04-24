package com.springboot.kafka;

import com.springboot.conf.KafkaProduct;
import com.springboot.model.KafkaRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private KafkaProduct<KafkaRequest> kafkaProduct;

    @Test
    public void kafkaTest() throws InterruptedException {
        //模拟发消息
        for (int i = 0; i < 5; i++) {

            KafkaRequest request = new KafkaRequest();
            request.setId(System.currentTimeMillis());
            request.setMessage(UUID.randomUUID().toString());
            request.setSendTime(new Date());

            kafkaProduct.send(request);
            Thread.sleep(3000);

        }
    }
}
