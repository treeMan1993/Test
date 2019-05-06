package com.example.test.net;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseApi {

    //带参数的通用get请求
    @GET()
    Call<ResponseBody> executeGet(@Url String url, @QueryMap Map<String, String> maps);

    //不带参数的通用get请求
    @GET()
    Call<ResponseBody> executeGet(@Url String url);

    //不带参数的通用post请求
    @POST()
    Call<ResponseBody> executePost(@Url String url);

    //带参数的通用post请求
    @POST()
    @FormUrlEncoded
    Call<ResponseBody> executePost(@Url String url, @FieldMap Map<String, String> maps);
}
