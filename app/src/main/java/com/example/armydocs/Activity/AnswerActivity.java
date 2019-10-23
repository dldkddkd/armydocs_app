package com.example.armydocs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.armydocs.R;


import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    public Bundle savedInstanceState;
    public AnswerActivity() { }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosurvey3);
    }
    public void BackButton(View v){
        finish();
    }
    public void finishSurvey(View v){
        Toast.makeText(getApplicationContext(),"답변이 등록되었습니다.",Toast.LENGTH_SHORT).show();
        finish();
    }
}