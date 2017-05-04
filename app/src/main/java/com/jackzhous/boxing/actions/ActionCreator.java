package com.jackzhous.boxing.actions;

import com.jack.retrofitrxjava2.api.NetApis;
import com.jack.retrofitrxjava2.manger.HttpManager;

/**
 * Created by jackzhous on 2017/5/4.
 */

public class ActionCreator {
    protected boolean hasAction = false;

    protected NetApis mNetApis = HttpManager.getInstance().getmNetApis();

    protected String  mType;

    private long timeStamp;         //防止连续点击

    protected boolean in2Second(){
        if(System.currentTimeMillis() - timeStamp < 2000){
            return true;
        }
        timeStamp = System.currentTimeMillis();
        return false;
    }

}
