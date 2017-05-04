package com.jack.retrofitrxjava2.response;

/**
 * Created by jackzhous on 2017/4/25.
 */

public class HttpResponse1<T> {
    private int code;
    private String message;
    private T rows;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
