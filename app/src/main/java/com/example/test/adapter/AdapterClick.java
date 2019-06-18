package com.example.test.adapter;

import android.view.View;

/**
 * Created by WeiT on 2018/5/3.
 */

public abstract class AdapterClick<T> {
    public abstract void Click(View view, int position, T t);

    public void LongClick(View view, int position, T t) {
    }

}
