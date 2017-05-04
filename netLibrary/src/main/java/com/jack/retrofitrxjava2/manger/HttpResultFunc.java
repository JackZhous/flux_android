package com.jack.retrofitrxjava2.manger;

import com.google.gson.Gson;
import com.jack.retrofitrxjava2.exception.ApiException;
import com.jack.retrofitrxjava2.response.HttpResponse;
import com.jack.retrofitrxjava2.util.JLog;

import io.reactivex.functions.Function;

/**
 * Created by jackzhous on 2017/4/24.
 */

public class HttpResultFunc<T> implements Function<HttpResponse<T>, T> {
    private static final int  SUCCESS = 1;
    private static final String TAG = "j_net";

    @Override
    public T apply(HttpResponse<T> tHttpResponse) throws Exception {
        JLog.i(TAG, new Gson().toJson(tHttpResponse));
        if(SUCCESS != tHttpResponse.getCode()){
            throw new ApiException(tHttpResponse.getCode(), tHttpResponse.getMessage());
        }

        return tHttpResponse.getDetail();
    }
}
