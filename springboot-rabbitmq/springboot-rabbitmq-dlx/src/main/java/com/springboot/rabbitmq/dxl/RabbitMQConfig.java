package com.springboot.rabbitmq.dxl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "DLX_EXCHANGE");
        args.put("x-dead-letter-routing-key", "KEY_R");
        args.put("x-message-ttl", 3 * 1000);
        return new Queue("DLX_QUEUE", true, false, false, args);
    }

    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("DLX_EXCHANGE");
    }

    @Bean
    public Binding bindDLX(@Qualifier("deadLetterQueue") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("KEY_DLX");
    }


    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return new Queue("DLX_REDIRECT_QUEUE");
    }

    @Bean
    public Binding redirectBinding(@Qualifier("redirectQueue") Queue queue, @Qualifier("deadLetterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("KEY_R");
    }
}
