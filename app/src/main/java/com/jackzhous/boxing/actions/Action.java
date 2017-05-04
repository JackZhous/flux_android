package com.jackzhous.boxing.actions;

/**
 * Created by jackzhous on 2017/5/4.
 */

public class Action<T> {

    private String type;

    private T      data;

    Action(String type, T data){
        this.type = type;
        this.data = data;
    }


    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
