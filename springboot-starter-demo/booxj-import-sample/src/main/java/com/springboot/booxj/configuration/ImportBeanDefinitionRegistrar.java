package com.springboot.booxj.configuration;

import com.springboot.booxj.service.impl.ImportService4Impl;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 11:04
 * @since
 */
public class ImportBeanDefinitionRegistrar implements org.springframework.context.annotation.ImportBeanDefinitionRegistrar {

    private final static String BEAN_NAME = "importService4Impl";


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (!registry.containsBeanDefinition(BEAN_NAME)) {
            System.out.println("start registerBeanDefinitions");
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(ImportService4Impl.class);
            beanDefinition.setSynthetic(true);

//            beanDefinition.getPropertyValues().add("className", "import4");

            ConstructorArgumentValues cav = new ConstructorArgumentValues();
            cav.addIndexedArgumentValue(0,"import4");
            beanDefinition.setConstructorArgumentValues(cav);
            registry.registerBeanDefinition(BEAN_NAME, beanDefinition);
        }

    }
}
