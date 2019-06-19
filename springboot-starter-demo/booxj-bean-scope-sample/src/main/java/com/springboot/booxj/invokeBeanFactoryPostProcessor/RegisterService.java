package com.springboot.booxj.invokeBeanFactoryPostProcessor;

import org.springframework.stereotype.Service;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 15:44
 * @since
 */
@Service
public class RegisterService {

    private String desc = "desc from RegisterService";

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getServiceDesc() {
        return desc;
    }

}
