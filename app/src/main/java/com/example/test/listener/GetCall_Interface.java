package com.example.test.listener;

import com.example.test.bean.BaseBean;
import com.example.test.bean.FirstViewData;
import com.example.test.bean.Login;
import com.example.test.bean.LoginInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetCall_Interface {

    @GET("wxarticle/list/408/1/json")
    Call<BaseBean<FirstViewData>> getCall();

    @POST("user/login")
    @FormUrlEncoded
    Call<BaseBean<Login>> login(@FieldMap Map<String,String> params);

    @POST("user/login")
    Call<BaseBean<Login>> login(@Body LoginInfo params);

}
