package com.jack.retrofitrxjava2.manger;

import com.google.gson.Gson;
import com.jack.retrofitrxjava2.api.NetApis;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.util.JLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jackzhous on 2017/4/21.
 */

public class HttpManager {
    private static final String TAG = "j_net";

    private static final String BASE_URL = "http://117.139.247.132:6080/box/";

    private static final int CINNECT_TIME = 5;

    private Retrofit         mRetrofit;

    private NetApis          mNetApis;


    private HttpManager(){
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
                                                         .connectTimeout(CINNECT_TIME, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder().client(okBuilder.build())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                            .baseUrl(BASE_URL)
                                            .build();

        mNetApis  = mRetrofit.create(NetApis.class);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpManager INSTANCE = new HttpManager();
    }

    //获取单例
    public static HttpManager getInstance(){
        return SingletonHolder.INSTANCE;
    }


    public NetApis getmNetApis(){
        return mNetApis;
    }

//    public void getLogin(Observer<UserBean> observer, HttpRequest request){
//
//        JLog.i(TAG, new Gson().toJson(request));
//        Observable observable = mNetApis.login(request).map(new HttpResultFunc<HttpResponse<UserBean>>());
//
//        toSubscribe(observable, observer);
//    }


    private <T> void toSubscribe(Observable<T> o, Observer<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }



}
