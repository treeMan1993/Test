package com.example.test.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.Constant;
import com.example.test.R;
import com.example.test.bean.BaseBean;
import com.example.test.bean.FirstViewData;
import com.example.test.bean.Login;
import com.example.test.bean.LoginInfo;
import com.example.test.listener.GetCall_Interface;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Retrofit retrofit;
    private OkHttpClient client;
    private GetCall_Interface call_interface;
    private Button btn_request;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("retrofitLog", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        call_interface = retrofit.create(GetCall_Interface.class);

    }

    private void initView() {
        btn_request = (Button) findViewById(R.id.btn_request);
        btn_request.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request:
                Call<BaseBean<FirstViewData>> call = call_interface.getCall();
                call.enqueue(new Callback<BaseBean<FirstViewData>>() {
                    @Override
                    public void onResponse(Call<BaseBean<FirstViewData>> call, Response<BaseBean<FirstViewData>> response) {
                        Log.e("SJH-response-body:", response.body().getData().getDatas().get(0).getNiceDate());
                        Log.e("SJH-response-message:", response.message());
                        Log.e("SJH-response-toString:", response.toString());
                    }

                    @Override
                    public void onFailure(Call<BaseBean<FirstViewData>> call, Throwable t) {
                        Log.e("SJH-call:", t.toString());
                    }
                });
                break;
            case R.id.btn_login:
                Map<String,String> params = new HashMap<>();
                params.put("username","hdhdhd");
                params.put("password","123456");
                Call<BaseBean<Login>> callLogin = call_interface.login(params);
                callLogin.enqueue(new Callback<BaseBean<Login>>() {
                    @Override
                    public void onResponse(Call<BaseBean<Login>> call, Response<BaseBean<Login>> response) {
                        Log.e("SJH-response-body:", response.body().getData().getId() + "");
                        Log.e("SJH-response-message:", response.message());
                        Log.e("SJH-response-toString:", response.toString());
                    }

                    @Override
                    public void onFailure(Call<BaseBean<Login>> call, Throwable t) {
                        Log.e("SJH-call:", t.toString());
                    }
                });
                break;
            default:
                break;
        }
    }
}
