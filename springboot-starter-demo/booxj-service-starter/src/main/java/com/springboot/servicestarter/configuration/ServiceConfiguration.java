package com.springboot.servicestarter.configuration;

import com.springboot.starter.api.service.AddService;
import com.springboot.starter.api.service.MinusService;
import com.springboot.starter.service.impl.AddServiceImpl;
import com.springboot.starter.service.impl.MinusServiceNotSupportNegativeImpl;
import com.springboot.starter.service.impl.MinusServiceSupportNegativeImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/**
 * 两种自动注入的方式
 * 1.使用@Import{@link org.springframework.context.annotation.Import}注解
 * 2.使用spring.factories 的 spi 机制
 * Configuration 处理类 ConfigurationClassPostProcessor {@link ConfigurationClassPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)}
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:25
 * @since
 */
@Configuration
@EnableConfigurationProperties
public class ServiceConfiguration {

    @Bean
    public AddService getAddService(){
        System.out.println("create addService");
        return new AddServiceImpl();
    }

    @Bean
//    @ConditionalOnProperty(prefix="starter.enable",name = "supportnegative", havingValue = "true") //环境变量 -Dstarter.enable.supportnegative=true
    @ConditionalOnProperty(value = "starter.enable.supportnegative",matchIfMissing = true)    //配置文件 starter.enable.supportnegative=true
    public MinusService getSupportMinusService(){
        System.out.println("create minusService support minus");
        return new MinusServiceSupportNegativeImpl();
    }

    @Bean
    @ConditionalOnMissingBean(MinusService.class)
    public MinusService getNotSupportMinusService(){
        System.out.println("create minusService not support minus");
        return new MinusServiceNotSupportNegativeImpl();
    }

}
