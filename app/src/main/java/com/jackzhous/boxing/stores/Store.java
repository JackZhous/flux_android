package com.jackzhous.boxing.stores;

import android.app.Notification;

import com.jackzhous.boxing.actions.Action;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jackzhous on 2017/5/4.
 */

public abstract class Store<T> {

    private static final EventBus bus = EventBus.getDefault();

    protected boolean     isSuccess;
    protected String      errorMsg;
    protected T           data;

    protected Store(){}

    public void register(Object object){
        bus.register(object);
    }


    public void unregister(Object object){
        bus.unregister(object);
    }

    void emitStoreChange(){
        bus.post(changeEvent());
    }

    //store发送给UI
    public abstract StoreChangeEvent changeEvent();

    //Dispatch发送给store
    public abstract void onAction(Action action);

    public static class StoreChangeEvent{}

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }
}
