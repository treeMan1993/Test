package com.example.test.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.test.Constant;
import com.example.test.R;
import com.example.test.bean.BaseBean;
import com.example.test.bean.FirstViewData;
import com.example.test.bean.Login;
import com.example.test.bean.LoginInfo;
import com.example.test.bean.User;
import com.example.test.listener.GetCall_Interface;
import com.example.test.net.IResponse;
import com.example.test.net.NetWorkManager;
import com.example.test.view_model.UserInfoViewModel;

import java.lang.reflect.Type;
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


    private Button btn_request;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


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
                Map<String,String> params = new HashMap<>();
                params.put("username","dhdhdh");
                params.put("password","123456");
                NetWorkManager.getInstance().doGetAsync("user/login", params, new IResponse<User>() {
                    @Override
                    public void success(User data) {
                        MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
                        mutableLiveData.setValue(data);
                    }

                    @Override
                    public void failure(Throwable t) {

                    }

                    @Override
                    public Type getDataType() {
                        return null;
                    }
                });
                break;
            case R.id.btn_login:

                break;
            default:
                break;
        }
    }
}
