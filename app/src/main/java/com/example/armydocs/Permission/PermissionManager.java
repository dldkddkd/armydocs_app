package com.example.armydocs.Permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

public class PermissionManager
{
    private static PermissionManager mInstance;

    private Activity mActivity;

    //privat static final int    MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private String[]        mPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static PermissionManager GetInst()
    {
        if (mInstance == null)
            mInstance = new PermissionManager();

        return mInstance;
    }

    public void Initialize(Activity _act)
    {
        // 퍼미션 획득에 관련된 데이터 할당 작업 및 퍼미션 획득 여부 확인
        SetActivity(_act);
        CheckPermissionDenied();
    }

    public void SetActivity(Activity _act)
    {
        mActivity = _act;
    }

    public void CheckPermissionDenied()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            GetPermissions();
            //onObtainingPermissionOverlayWindow();
        }
    }

    /**
     * 퍼미션 등록 : 전화걸기, 음성녹음
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void GetPermissions()
    {
        mActivity.requestPermissions(mPermissions, 1000);
    }

    /**
     * 오버레이에 관한 퍼미션 등록
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onObtainingPermissionOverlayWindow()
    {
        if (!Settings.canDrawOverlays(mActivity.getApplicationContext()))
        {
            Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + mActivity.getPackageName()));
            mActivity.startActivityForResult(myIntent, 101);
        }
    }
}
