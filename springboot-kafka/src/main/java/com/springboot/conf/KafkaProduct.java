package com.springboot.conf;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProduct<T> {

    private Logger logger = LoggerFactory.getLogger(KafkaProduct.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(T obj) {
        String jsonObj = JSON.toJSONString(obj);
        logger.info("------------ message = {}", jsonObj);

        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka.tut", jsonObj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //TODO 业务处理
                logger.info("Produce: The message was sent successfully:");
                logger.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
            }
        });
    }
}
