package com.example.test.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.bean.Login;
import com.example.test.bean.User;

/**
 * Created by SunJH on 2019/5/6
 **/
public class UserInfoViewModel extends ViewModel {
    private String name;
    private LiveData<Login> user;

    public void setUser(LiveData<Login> user) {
        this.user = user;
    }

    public void init(String name) {
        this.name = name;
    }

    public LiveData<Login> getUser() {
        return user;
    }
}
