package com.springboot.booxj.registerBeanPostProcessors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * 改变实例化的对象
 * <p>
 *  注册process
 *  AbstractApplicationContext.refresh()->registerBeanPostProcessors(beanFactory)->
 *  PostProcessorRegistrationDelegate.registerBeanPostProcessors
 *
 * 执行
 *  AbstractAutowireCapableBeanFactory.initializeBean()->applyBeanPostProcessorsBeforeInitialization()->postProcessAfterInitialization()
 */
@Configuration
public class CustomizeBeanPostProcessor implements BeanPostProcessor  {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("beanService".equals(beanName)){
            System.out.println("CustomizeBeanPostProcessor : do postProcess before initialization");
            BeanService beanService = (BeanService) bean;
            ((BeanService) bean).setDesc("Desc is changed from bean post processor before initialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("beanService".equals(beanName)){
            System.out.println("CustomizeBeanPostProcessor : do postProcess before initialization");
            BeanService beanService = (BeanService) bean;
            ((BeanService) bean).setDesc("Desc is changed from bean post processor after initialization");
        }
        return bean;
    }
}
