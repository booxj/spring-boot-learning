package com.booxj.web;

import com.booxj.bean.YmlBean;
import com.booxj.bean.PropertyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wb
 * @data: 2018/1/11 9:25
 * @see:
 * @since:
 */
@RestController
public class LucyController {

    @Autowired
    YmlBean ymlBean;

    @Autowired
    PropertyBean propertyBean;

    @GetMapping("yml")
    public String yml() {
        return ymlBean.getGreeting() + " >>>>" + ymlBean.getName() + " >>>>" + ymlBean.getUuid() + " >>>>" + ymlBean.getMax();
    }

    @GetMapping("property")
    public String property() {
        return propertyBean.getName() + ":" + propertyBean.getAge();
    }
}
