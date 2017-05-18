package com.example.win.mypermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private static final int PERMISSIONS_REQUEST_TAKE_PHOTO = 1;
    private static final int REQUECT_CODE_SDCARD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.sdcard_btn);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAllowTakePicture();
//                MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        });
    }

    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @PermissionGrant(REQUECT_CODE_SDCARD)
//    public void requestSdcardSuccess() {
//        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
//    }
//
//    @PermissionDenied(REQUECT_CODE_SDCARD)
//    public void requestSdcardFailed() {
//        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
//    }

    private void checkAllowTakePicture() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有此权限，去请求权限
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            //有权限，直接回去做后续操作
            Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_TAKE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "拍照", Toast.LENGTH_LONG).show();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
