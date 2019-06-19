package com.springboot.starter.service.impl;

import com.springboot.starter.api.service.AddService;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:15
 * @since
 */
public class AddServiceImpl implements AddService {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
