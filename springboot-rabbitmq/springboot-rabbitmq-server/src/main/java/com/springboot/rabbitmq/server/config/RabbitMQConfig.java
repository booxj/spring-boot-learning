package com.springboot.rabbitmq.server.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义三个队列first,second,third，两个交换机topic,fanout
 *
 * 将三个队列绑定到topic,对应的路由分别为A.# B.# C.# ; 发送消息时指定路由规则，交换机根据路由规则将消息分发到对应的queue
 *
 * 将三个队列绑定到fanout,消息广播到所有的绑定队列
 *
 */
@Configuration
public class RabbitMQConfig {

    // 三个队列
    @Bean("firstQueue")
    public Queue getFirstQueue() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-message-ttl", 6000);
//        Queue queue = new Queue("FIRST_QUEUE", false, false, true, args);
        Queue queue = new Queue("FIRST_QUEUE");
        return queue;
    }

    @Bean("secondQueue")
    public Queue getSecondQueue() {
        return new Queue("SECOND_QUEUE");
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue() {
        return new Queue("THIRD_QUEUE");
    }


    // 两个交换机
    @Bean("topicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("FANOUT_EXCHANGE");
    }


    // 绑定
    @Bean
    public Binding firstBindTopic(@Qualifier("firstQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("A.#");
    }

    @Bean
    public Binding secondBindTopic(@Qualifier("secondQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("B.#");
    }

    @Bean
    public Binding thirdBindTopic(@Qualifier("thirdQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("C.#");
    }


    @Bean
    public Binding firstBindFanout(@Qualifier("firstQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding secondBindFanout(@Qualifier("secondQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding thirdBindFanout(@Qualifier("thirdQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
