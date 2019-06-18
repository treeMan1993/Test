package com.example.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;
import com.example.test.bean.BaseBean;
import com.example.test.bean.Login;
import com.example.test.net.BaseApi;
import com.example.test.net.IResponse;
import com.example.test.net.NetWorkManager;
import com.example.test.utils.LogUtil;
import com.example.test.view_model.UserInfoViewModel;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends FragmentActivity implements View.OnClickListener {
    private UserInfoViewModel userInfoViewModel;
    private Button mBtnChangeData;
    private TextView mTvShow;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        Map<String, String> params = new HashMap<>();
        params.put("username", "hdhdhd");
        params.put("password", "123456");
        userInfoViewModel.init();
        userInfoViewModel.requestUserInfo(params);
        userInfoViewModel.getUser().observe(this,login->{
            mTvShow.setText(String.valueOf(login.getData().getId()));
        });
    }

    private void initView() {
        mBtnChangeData = (Button) findViewById(R.id.btn_change_data);
        mTvShow = (TextView) findViewById(R.id.tv_show);
        mBtnChangeData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_data:
               BaseApi baseApi = NetWorkManager.getInstance().newBuilder().build(BaseApi.class);
                Map<String, String> params = new HashMap<>();
                params.put("username", "hdhdhd");
                params.put("password", "123456");
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
        }
    }
}
