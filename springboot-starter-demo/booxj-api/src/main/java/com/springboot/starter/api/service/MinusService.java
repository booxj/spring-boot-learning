package com.springboot.starter.api.service;

import com.springboot.starter.api.exception.MinusException;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:13
 * @since
 */
public interface MinusService {

    int minus(int minuend, int subtraction) throws MinusException;
}
