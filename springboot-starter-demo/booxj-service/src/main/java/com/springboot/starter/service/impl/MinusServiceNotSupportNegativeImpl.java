package com.springboot.starter.service.impl;

import com.springboot.starter.api.exception.MinusException;
import com.springboot.starter.api.service.MinusService;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:16
 * @since
 */
public class MinusServiceNotSupportNegativeImpl implements MinusService {

    /**
     * 减法运算，不支持负数结果，如果被减数小于减数，就跑出MinusException
     *
     * @param minuend     被减数
     * @param subtraction 减数
     * @return
     * @throws MinusException
     */
    @Override
    public int minus(int minuend, int subtraction) throws MinusException {
        if (subtraction > minuend) {
            throw new MinusException("not support negative!");
        }

        return minuend - subtraction;
    }
}
