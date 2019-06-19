package com.springboot.booxj.prepareBeanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * // 创建一个 bean 后置处理器
 * AbstractApplicationContext
 * prepareBeanFactory(beanFactory)->beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this))
 * <p>
 * // ApplicationContextAwareProcessor 的 postProcessBeforeInitialization 方法在每个 bean 初始化之前被调用一次
 * ApplicationContextAwareProcessor
 * postProcessBeforeInitialization()->invokeAwareInterfaces(bean)
 * <p>
 * 常见的一些aware
 * BeanNameAware：获取容器中bean的名称
 * BeanFactoryAware：获取当前的 bean factory
 * ApplicationContextAware：获取当前的 applicationContext
 * MessageSourceAware：获得 message source
 * applicationEventPublisherAware：应用事件发布器，可以发布事件
 * ResourceLoaderAware：获得资源加载器，可以获得外部资源文件的内容
 */
@Configuration
public class CustomizeAware implements ApplicationContextAware, EnvironmentAware, BeanNameAware {

    private ApplicationContext applicationContext;
    private Environment environment;
    private String beanName;

    public ApplicationContext getApplicationContext() {
        System.out.println();
        return applicationContext;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("CustomizeAware : applicationContext is set to " + applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("CustomizeAware : environment is set to " + environment);
        this.environment = environment;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("CustomizeAware : beanName is set to " + beanName);
        this.beanName = beanName;
    }
}
