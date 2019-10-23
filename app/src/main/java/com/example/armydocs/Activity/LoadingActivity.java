package com.example.armydocs.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.Permission.PermissionManager;
import com.example.armydocs.R;


public class LoadingActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PermissionManager.GetInst().Initialize(this);
        setContentView(R.layout.activity_loading);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            //startActivity(new Intent(LoadingActivity.this,MainActivity.class));
            finish();
            }
        },3000);

    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strings, int[] ints)
    {
        if (ints.length > 0 && ints[0] == PackageManager.PERMISSION_GRANTED)
        {
            // 권한 획득에 성공하였을 때 수행할 내용
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            // 권한 획득에 실패했을 때 퍼미션을 거부했으면 어플리케이션 강제 종료.
            moveTaskToBack(true);
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

}
