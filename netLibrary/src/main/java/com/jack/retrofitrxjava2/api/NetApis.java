package com.jack.retrofitrxjava2.api;


import com.jack.retrofitrxjava2.model.BoxBean;
import com.jack.retrofitrxjava2.model.BoxDetailRecieve;
import com.jack.retrofitrxjava2.model.BoxDetailSend;
import com.jack.retrofitrxjava2.model.LoginBean;
import com.jack.retrofitrxjava2.model.MapSearchBean;
import com.jack.retrofitrxjava2.model.MyList;
import com.jack.retrofitrxjava2.model.ProvinceBean;
import com.jack.retrofitrxjava2.model.TokenBean;
import com.jack.retrofitrxjava2.model.UserBean;
import com.jack.retrofitrxjava2.request.HttpRequest;
import com.jack.retrofitrxjava2.response.HttpResponse;
import com.jack.retrofitrxjava2.response.HttpResponse1;
import com.jack.retrofitrxjava2.response.HttpResponse2;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jackzhous on 2017/4/21.
 */

public interface NetApis {

    @POST("app")
    Observable<HttpResponse<TokenBean>> login(@Body HttpRequest<LoginBean> beanHttpRequest);


    @POST("app")
    Observable<HttpResponse<UserBean>> userInfo(@Body HttpRequest<TokenBean> beanHttpRequest);

    @POST("app")
    Observable<HttpResponse1<List<ProvinceBean>>> getAllProvince(@Body HttpRequest objectHttpRequest);

    @POST("app")
    Observable<HttpResponse<MyList<List<BoxBean>>>> mapSearch(@Body HttpRequest<MapSearchBean> beanHttpRequest);

    @POST("app")
    Observable<HttpResponse2<BoxDetailRecieve>> getDetailData(@Body HttpRequest<BoxDetailSend> boxDetailSendHttpRequest);
}
