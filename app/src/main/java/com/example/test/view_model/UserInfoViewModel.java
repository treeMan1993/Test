package com.example.test.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.bean.BaseBean;
import com.example.test.bean.Login;
import com.example.test.bean.User;
import com.example.test.data.UserRepository;
import com.example.test.net.IResponse;
import com.example.test.net.NetWorkManager;
import com.example.test.utils.LogUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by SunJH on 2019/5/6
 **/
public class UserInfoViewModel extends ViewModel {
    private MutableLiveData<BaseBean<Login>> user;
    UserRepository repository;

    public void init() {
        user = new MutableLiveData<>();
        repository = new UserRepository();
    }

    public MutableLiveData<BaseBean<Login>> getUser() {
        return user;
    }

    public void requestUserInfo(Map<String, String> params) {
        repository.getUser(params,user);
    }
}
