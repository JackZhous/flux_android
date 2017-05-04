package com.jack.retrofitrxjava2.response;

/**
 * Created by jackzhous on 2017/4/25.
 */

public class HttpResponse2<T>  {
    private int code;
    private String message;
    private T detai;

    public T getDetai() {
        return detai;
    }

    public void setDetai(T detai) {
        this.detai = detai;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
