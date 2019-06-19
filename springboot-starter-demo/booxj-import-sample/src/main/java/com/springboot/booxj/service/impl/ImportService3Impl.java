package com.springboot.booxj.service.impl;

import com.springboot.booxj.service.ImportService3;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 11:01
 * @since
 */
public class ImportService3Impl implements ImportService3 {

    @Override
    public void execute() {
        System.out.println("execute : " + this.getClass().getSimpleName());
    }
}
