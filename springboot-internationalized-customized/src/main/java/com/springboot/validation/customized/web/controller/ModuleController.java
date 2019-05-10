package com.springboot.validation.customized.web.controller;

import com.springboot.validation.customized.utils.I18nUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ModuleController.java
 * @Description: TODO
 * @Author: booxj
 * @CreateDate 2019/5/10 11:55
 * @version:
 */
@RestController
public class ModuleController {


    @GetMapping("/module")
    public String hello() {
        System.out.println("module.title: " + I18nUtil.getMessage("module.title"));
        System.out.println("title: " + I18nUtil.getMessage("title"));
        System.out.println("name: " + I18nUtil.getMessage("name", new Object[]{"spring"}));
        System.out.println(I18nUtil.getLocale());
        return "module";
    }
}
