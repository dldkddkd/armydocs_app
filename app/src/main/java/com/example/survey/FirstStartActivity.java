package com.example.survey;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class FirstStartActivity extends  AppCompatActivity{
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences preference = getSharedPreferences("a", MODE_PRIVATE);
        SharedPreferences.Editor ed = preference.edit();
        ed.putBoolean("tutorial",false);
        ed.commit();
        setContentView(R.layout.activity_first_start);
    }
}
