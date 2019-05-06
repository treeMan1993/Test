package com.example.test.net;

import com.example.test.BuildConfig;
import com.example.test.Constant;
import com.example.test.utils.LogUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {
    private static volatile NetWorkManager netWorkManager;
    private Retrofit retrofit;
    private OkHttpClient client;
    private long CONNECT_TIME_OUT = 10_000;
    private long READ_TIME_OUT = 20_000;
    private String TAG = "NetWorkManager";
    BaseApi baseApi;

    private NetWorkManager() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e(TAG, message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor);
        client = builder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .build();
        baseApi = retrofit.create(BaseApi.class);
    }

    public static NetWorkManager getInstance() {
        if (netWorkManager == null) {
            synchronized (NetWorkManager.class) {
                if (netWorkManager == null) {
                    netWorkManager = new NetWorkManager();
                }
            }
        }
        return netWorkManager;
    }

    public NetWorkManager.Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        Retrofit retrofit;
        OkHttpClient client;
        NetWorkManager netWorkManager;

        Builder(NetWorkManager netWorkManager) {
            this.netWorkManager = netWorkManager;
        }

        public NetWorkManager.Builder setRetrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        public NetWorkManager.Builder setClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public <T> T build(Class<T> cla) {
            if (retrofit == null) retrofit = netWorkManager.retrofit;
            if (client == null) client = netWorkManager.client;
           Retrofit mRetrofit = retrofit.newBuilder()
                    .client(client)
                    .build();
            return mRetrofit.create(cla);
        }
    }

    /**
     * 通用get请求
     * 如果有参数就传params，没有参数params传null
     */
    public <T> void doGetAsync(final String url, Map<String, String> params, final IResponse<T> res) {
        Call<ResponseBody> call = params == null ? baseApi.executeGet(url) : baseApi.executeGet(url, params);
        try {
            //异步请求
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (res == null) {
                        return;
                    }
                    try {
                        Class<?> clas = baseApi.getClass();
                        Method method = clas.getMethod("executeGet", new Class[]{String.class});
                        //根据resonse的getDataType来决定返回的类型
                        Converter converter = retrofit.responseBodyConverter(res.getDataType(), method.getAnnotations());
                        if (converter != null) {
                            T data = (T) converter.convert(response.body());
                            res.success(data);
                        }
                    } catch (Exception e) {
                        res.failure(e);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (res != null) res.failure(t);
                }
            });

        } catch (Exception e) {
            if (res != null) res.failure(e);
        }
    }

    /**
     * 通用post请求
     * 如果有参数就传params，没有参数params传null
     */
    public <T> void doPostAsync(final String url, Map<String, String> params, final IResponse<T> res) {
        Call<ResponseBody> call = params == null ? baseApi.executePost(url) : baseApi.executePost(url, params);
        try {
            //异步请求
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (res == null) {
                        return;
                    }
                    try {
                        Class<?> clas = baseApi.getClass();
                        Method method = clas.getMethod("executePost", new Class[]{String.class});
                        //根据resonse的getDataType来决定返回的类型
                        Converter converter = retrofit.responseBodyConverter(res.getDataType(), method.getAnnotations());
                        if (converter != null) {
                            T data = (T) converter.convert(response.body());
                            res.success(data);
                        }
                    } catch (Exception e) {
                        res.failure(e);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (res != null) res.failure(t);
                }
            });

        } catch (Exception e) {
            LogUtil.e("SJH", e.toString());
            if (res != null) res.failure(e);
        }
    }

}
