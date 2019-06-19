package com.springboot.booxj.prepareBeanFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

/**
 * AbstractApplicationContext
 */
@Component
public class CustomizePublisher implements ApplicationEventPublisherAware,ApplicationContextAware {

    private ApplicationEventPublisher publisher;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 发送一条广播
     */
    public void publishEvent(){
        publisher.publishEvent(new CustomizeEvent(applicationContext));
    }

    static class CustomizeEvent extends ApplicationContextEvent {

        public CustomizeEvent(ApplicationContext source) {
            super(source);
        }
    }
}
