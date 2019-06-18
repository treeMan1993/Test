package com.example.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.net.IResponse;
import com.example.test.net.NetWorkManager;
import com.example.test.utils.LogUtil;

import java.lang.reflect.Type;

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

                NetWorkManager.getInstance().doGetAsync("https://www.jianshu.com/p/4268e434150a",null, new IResponse<String>() {
                    @Override
                    public void success(String data) {
                        LogUtil.e("SJH",data);
                    }

                    @Override
                    public void failure(Throwable t) {

                    }

                    @Override
                    public Type getDataType() {
                        return String.class;
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
