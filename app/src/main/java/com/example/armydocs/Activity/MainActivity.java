package com.example.armydocs.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.armydocs.R;
import com.example.armydocs.Util.MyPagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private void CheckFirstTime()
    {
        SharedPreferences   pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean             first = pref.getBoolean("isFirst", false);

        if (first == false)
        {
            SharedPreferences.Editor    editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
        }
    }

    private void Initialize()
    {
        CheckFirstTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Initialize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = findViewById(R.id.vp_pager1);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //setContentView(R.layout.welcome_slide4);
    }
    public void MoveToLogin(View v){
       startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void MoveToHome(View v){
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {}
}
