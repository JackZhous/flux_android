package com.jackzhous.boxing.dispatcher;

import com.jackzhous.boxing.actions.Action;
import com.jackzhous.boxing.stores.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackzhous on 2017/5/4.
 */

public class Dispatcher {

    private static Dispatcher instance;

    private final List<Store> stores = new ArrayList<>();

    private Dispatcher(){}

    public static Dispatcher get(){
        if(instance == null){
            instance = new Dispatcher();
        }

        return instance;
    }


    public void register(Store store){
        if(!stores.contains(store)){
            stores.add(store);
        }
    }


    public void unregister(Store store){
        stores.remove(store);
    }

    public void dispatch(Action action){
        for(Store store : stores){
            store.onAction(action);
        }
    }


}
