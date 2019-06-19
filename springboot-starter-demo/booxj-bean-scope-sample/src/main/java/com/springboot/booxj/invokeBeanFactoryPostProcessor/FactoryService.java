package com.springboot.booxj.invokeBeanFactoryPostProcessor;

import org.springframework.stereotype.Service;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 15:22
 * @since
 */
@Service("helloService")
public class FactoryService {

    private String desc = "desc from FactoryService";

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getServiceDesc() {
        return desc;
    }

}
