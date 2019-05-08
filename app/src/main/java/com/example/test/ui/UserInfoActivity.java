package com.example.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;
import com.example.test.bean.Login;
import com.example.test.view_model.UserInfoViewModel;

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
        userInfoViewModel.init("SunJH");
        userInfoViewModel.getUser().observe(this,login->{
            mTvShow.setText(String.valueOf(login.getId()));
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
                i++;
                userInfoViewModel.getUser().getValue().setId(i);
                break;
        }
    }
}
