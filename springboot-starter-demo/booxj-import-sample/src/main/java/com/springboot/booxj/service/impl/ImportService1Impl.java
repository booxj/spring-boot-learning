package com.springboot.booxj.service.impl;

import com.springboot.booxj.service.ImportService1;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 11:01
 * @since
 */
public class ImportService1Impl implements ImportService1 {

    @Override
    public void execute() {
        System.out.println("execute : " + this.getClass().getSimpleName());
    }
}
