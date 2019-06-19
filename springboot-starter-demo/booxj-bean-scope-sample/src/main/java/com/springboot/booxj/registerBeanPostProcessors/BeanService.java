package com.springboot.booxj.registerBeanPostProcessors;

import org.springframework.stereotype.Service;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 16:07
 * @since
 */
@Service
public class BeanService {

    private String desc = "desc from BeanService";

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getServiceDesc() {
        return desc;
    }
}
