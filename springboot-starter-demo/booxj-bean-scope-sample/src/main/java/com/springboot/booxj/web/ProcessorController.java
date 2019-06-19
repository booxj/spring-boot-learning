package com.springboot.booxj.web;

import com.springboot.booxj.invokeBeanFactoryPostProcessor.FactoryService;
import com.springboot.booxj.invokeBeanFactoryPostProcessor.RegisterService;
import com.springboot.booxj.registerBeanPostProcessors.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 15:25
 * @since
 */
@RestController
public class ProcessorController {

    @Autowired
    private FactoryService factoryService;

    @Autowired
    @Qualifier("customizeRegisterService")
    private RegisterService registerService;

    @Autowired
    private BeanService beanService;

    @RequestMapping("factory")
    public String postProcessor() {
        return factoryService.getServiceDesc();
    }

    @RequestMapping("register")
    public String register() {
        return registerService.getServiceDesc();
    }

    @RequestMapping("bean")
    public String bean(){
        return beanService.getServiceDesc();
    }
}
