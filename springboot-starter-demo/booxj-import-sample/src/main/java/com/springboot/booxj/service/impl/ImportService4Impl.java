package com.springboot.booxj.service.impl;

import com.springboot.booxj.service.ImportService4;
import org.springframework.util.StringUtils;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 11:01
 * @since
 */
public class ImportService4Impl implements ImportService4 {

    private String className;

    public ImportService4Impl() {
    }

    public ImportService4Impl(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void execute() {
        String name = StringUtils.isEmpty(className) ? this.getClass().getSimpleName() : className;
        System.out.println("execute : " + name);
    }
}
