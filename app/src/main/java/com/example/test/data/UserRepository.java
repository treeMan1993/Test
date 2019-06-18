package com.example.test.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.test.bean.BaseBean;
import com.example.test.bean.Login;
import com.example.test.bean.User;
import com.example.test.net.IResponse;
import com.example.test.net.NetWorkManager;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by skyu on 2019/4/29.
 * 邮箱:yaphets0327@gmail.com
 * 描述:
 */
public class UserRepository {

    public void getUser(Map<String,String> params,MutableLiveData<BaseBean<Login>> loginData){
        NetWorkManager.getInstance().doPostAsync("user/login", params, new IResponse<BaseBean<Login>>() {
            @Override
            public void success(BaseBean<Login> data) {
                loginData.setValue(data);
            }

            @Override
            public void failure(Throwable t) {

            }

            @Override
            public Type getDataType() {
                Type type = new TypeToken<BaseBean<Login>>(){}.getType();
                return type;
            }
        });
    }

}
