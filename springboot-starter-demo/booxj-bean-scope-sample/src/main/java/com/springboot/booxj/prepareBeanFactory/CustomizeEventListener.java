package com.springboot.booxj.prepareBeanFactory;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 14:28
 * @since
 */
@Component
public class CustomizeEventListener implements ApplicationListener<CustomizePublisher.CustomizeEvent> {

    @Override
    public void onApplicationEvent(CustomizePublisher.CustomizeEvent event) {
        System.out.println("onApplicationEvent : " + event);
    }
}
