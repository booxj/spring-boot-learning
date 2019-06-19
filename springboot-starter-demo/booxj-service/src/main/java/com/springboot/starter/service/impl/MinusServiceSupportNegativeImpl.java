package com.springboot.starter.service.impl;

import com.springboot.starter.api.exception.MinusException;
import com.springboot.starter.api.service.MinusService;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:17
 * @since
 */
public class MinusServiceSupportNegativeImpl implements MinusService {

    @Override
    public int minus(int minuend, int subtraction) throws MinusException {
        return minuend - subtraction;
    }
}
