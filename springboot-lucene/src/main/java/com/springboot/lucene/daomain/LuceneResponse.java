package com.springboot.lucene.daomain;

import com.springboot.core.domain.Response;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/11 15:44
 * @since
 */
public class LuceneResponse<T> extends Response {

    long total;

    public LuceneResponse(int code, T date, String msg) {
        super(code, date, msg);
    }

    public LuceneResponse(int code, T date, String msg, long total) {
        super(code, date, msg);
        this.total = total;
    }

    public static <T> LuceneResponse ok(T date, long total) {
        return new LuceneResponse(200, date, "ok", total);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
