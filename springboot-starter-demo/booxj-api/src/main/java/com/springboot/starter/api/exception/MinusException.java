package com.springboot.starter.api.exception;

/**
 * 减法异常
 * <p>
 *
 * @author booxj
 * @create 2019/6/19 9:09
 * @since
 */
public class MinusException extends RuntimeException {

    public MinusException(String message) {
        super(message);
    }
}
