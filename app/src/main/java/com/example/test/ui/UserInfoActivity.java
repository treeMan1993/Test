package com.example.test.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;
import com.example.test.bean.User;
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
        userInfoViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mTvShow.setText(String.valueOf(i));
            }
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
                userInfoViewModel.getUser().getValue().setName(String.valueOf(i));
                break;
        }
    }
}
