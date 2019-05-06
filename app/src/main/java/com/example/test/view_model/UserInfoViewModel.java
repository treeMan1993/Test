package com.example.test.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.bean.User;

/**
 * Created by SunJH on 2019/5/6
 **/
public class UserInfoViewModel extends ViewModel {
    private String name;

    public void setUser(LiveData<User> user) {
        this.user = user;
    }

    private LiveData<User> user;

    public void init(String name) {
        this.name = name;
    }

    public LiveData<User> getUser() {
        return user;
    }
}
