package com.springboot.booxj.invokeBeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;

/**
 * BeanDefinitionRegistryPostProcessor继承了BeanFactoryPostProcessor接口，所以具有BeanFactoryPostProcessor的能力
 * 除此之外，还可以通过postProcessBeanDefinitionRegistry()注册更多的bean到spring容器中
 * <p>
 * AbstractApplicationContext.refresh()->invokeBeanFactoryPostProcessors(beanFactory)->
 * PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors()
 */
@Configuration
public class CustomizeBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition registerServiceBean = new RootBeanDefinition(RegisterService.class);
        registerServiceBean.getPropertyValues().addPropertyValue("desc","Desc is changed from bean definition registry post processor");
        registry.registerBeanDefinition("customizeRegisterService", registerServiceBean);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
