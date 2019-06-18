package com.example.test.easyPermission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.utils.LogUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class EasyPermissionActivity extends AppCompatActivity implements View.OnClickListener,EasyPermissions.PermissionCallbacks {
    private String TAG = getClass().getSimpleName();
    private Button btn_request_permission;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_permission);
        findId();

    }

    private void findId() {
        btn_request_permission = (Button) findViewById(R.id.btn_request_permission);
        btn_request_permission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request_permission:
                cameraTask();
                break;
        }
    }
    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        EasyPermissions.requestPermissions(
                this,
                "访问相机需要权限",
                RC_CAMERA_PERM,
                Manifest.permission.CAMERA);

//        if (hasCameraPermission()) {
//            // Have permission, do the thing!
//            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
//        } else {
//            // Ask for one permission
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    private boolean hasCameraPermission() {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        LogUtil.e(TAG,"同意了权限");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        LogUtil.e(TAG,"拒绝了权限");
    }
}
