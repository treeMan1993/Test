package com.example.test.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.utils.QRCodeUtil;

public class QRCodeTestActivity extends AppCompatActivity {

    private ImageView mAppImgShowQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_test);
        findId();
    }

    private void findId() {
        mAppImgShowQrcode = (ImageView) findViewById(R.id.app_img_show_qrcode);
        Bitmap bitmap = QRCodeUtil.createQRCodeBitmap("www.baidu.com",400,400);
        mAppImgShowQrcode.setImageBitmap(bitmap);
    }
}
