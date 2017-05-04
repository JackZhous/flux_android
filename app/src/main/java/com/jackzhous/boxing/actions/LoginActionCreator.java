package com.jackzhous.boxing.actions;


import com.google.gson.Gson;
import com.jack.retrofitrxjava2.model.LoginBean;
import com.jack.retrofitrxjava2.model.TokenBean;
import com.jack.retrofitrxjava2.model.UserBean;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.response.HttpResponse;
import com.jack.retrofitrxjava2.util.JLog;
import com.jackzhous.boxing.dispatcher.Dispatcher;
import com.jackzhous.boxing.encrypt.MD5Encrypt;
import com.jackzhous.boxing.utils.Constant;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jackzhous on 2017/5/4.
 */

public class LoginActionCreator extends ActionCreator{



    @SuppressWarnings("unchecked")
    public void actionCreate( String type, String username, String passwd){

        if(hasAction || in2Second()){
            return;
        }

        hasAction = true;
        mType = type;
        HttpRequest request = new HttpRequest();
        request.setCmd(type);
        LoginBean bean = new LoginBean();
        bean.setMobile(username);
        bean.setPassword(MD5Encrypt.MD5(passwd));
        request.setParams(bean);


        mNetApis.login(request)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(login);
    }


    @SuppressWarnings("unchecked")
    private void getUserInfo( String type, TokenBean token){
        HttpRequest request = new HttpRequest();
        request.setCmd(type);

        request.setParams(token);
        mType = type;
        JLog.i("j_tag", "---"+new Gson().toJson(request));
        mNetApis.userInfo(request)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userInfo);
    }


    private Observer<HttpResponse<UserBean>> userInfo = new Observer<HttpResponse<UserBean>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(HttpResponse<UserBean> value) {
            Action<HttpResponse<UserBean>> action = new Action<>(mType, value);
            Dispatcher.get().dispatch(action);
        }

        @Override
        public void onError(Throwable e) {
            JLog.i("j_tag", "exception");
            Action<HttpResponse> action = new Action<>(mType, new HttpResponse(Constant.HTTP_FAILED, Constant.HTTP_ERROR));
            Dispatcher.get().dispatch(action);
            hasAction = false;
        }

        @Override
        public void onComplete() {
            hasAction = false;
        }

    };


    private Observer<HttpResponse<TokenBean>> login = new Observer<HttpResponse<TokenBean>>() {
        @Override
        public void onSubscribe(Disposable d) {
            JLog.i("j_tag", "onSubscribe");
        }

        @Override
        public void onNext(HttpResponse<TokenBean> value) {
            JLog.i("j_tag", new Gson().toJson(value));
            if(value.getCode() == Constant.HTTP_SUCCESS){
                getUserInfo(Constant.ACTION_USERINFGO_TYPE, value.getDetail());
            }else{
                Action<HttpResponse<TokenBean>> action = new Action<>(mType, value);
                Dispatcher.get().dispatch(action);
                hasAction = false;
            }

        }

        @Override
        public void onError(Throwable e) {
            JLog.i("j_tag", "exception");
            Action<HttpResponse> action = new Action<>(mType, new HttpResponse(Constant.HTTP_FAILED, Constant.HTTP_ERROR));
            Dispatcher.get().dispatch(action);
            hasAction = false;
        }

        @Override
        public void onComplete() {
        }
    };
}
