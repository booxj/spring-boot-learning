package com.springboot.booxj.configuration;

import com.springboot.booxj.service.impl.ImportService1Impl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Import{@link Import} 加载过载
 * 1. 普通类（即没有实现ImportBeanDefinitionRegistrar、ImportSelector、DeferredImportSelector等接口的类）会通过ConfigurationClassBeanDefinitionReader.loadBeanDefinitionsFromImportedResources方法将bean定义注册到spring容器；
 * 2. ImportSelector 实现类，其selectImports方法返回的bean的名称，通过ConfigurationClassParser类的asSourceClass方法转成SourceClass对象，然后被当作普通类处理；
 * 3. DeferredImportSelector 实现类的处理和ImportSelector实现类的处理并无区别，只是处理时机比起ImportSelector实现类略晚；
 * 4. ImportBeanDefinitionRegistrar 实现类的registerBeanDefinitions方法会被调用，里面可以注册业务所需的bean定义；
 * <p>
 * 四种Import用法区别
 * 1.如果Abc类实现了ImportSelector接口，spring容器就会实例化Abc类，并且调用其selectImports方法
 * 2.DeferredImportSelector是ImportSelector的子类，不同的是要等到@Configuration注解中相关的业务全部都处理完了才会调用selectImports方法
 * 3.如果Abc类实现了ImportBeanDefinitionRegistrar接口，spring容器就会实例化Abc类，并且调用其registerBeanDefinitions方法
 * 4.如果Abc没有实现ImportSelector、DeferredImportSelector、ImportBeanDefinitionRegistrar等其中的任何一个，spring容器就会直接实例化Abc类
 * <p>
 */
@Configuration
@Import({
        ImportService1Impl.class,
        ImportSelector.class,
        DeferredImportSelector.class,
        ImportBeanDefinitionRegistrar.class
})
public class ImportConfiguration {
}
