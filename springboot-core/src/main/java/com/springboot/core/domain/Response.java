package com.springboot.core.domain;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/11 16:00
 * @since
 */
public class Response<T> {

    private int code;
    private T date;
    private String msg;


    public Response(int code, T date, String msg) {
        this.code = code;
        this.date = date;
        this.msg = msg;
    }

    public static Response ok() {
        return new Response(200, null, "ok");
    }

    public static <T> Response ok(T date) {
        return new Response(200, date, "ok");
    }

    public static Response fail() {
        return new Response(500, null, "fail");
    }

    public static Response fail(String msg) {
        return new Response(500, null, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
