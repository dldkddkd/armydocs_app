package com.example.armydocs.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;

public class FAQActivity extends AppCompatActivity {

    FAQActivity(){ }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_dev);

    }
    public void BackButton(View v){
        finish();
    }
}
