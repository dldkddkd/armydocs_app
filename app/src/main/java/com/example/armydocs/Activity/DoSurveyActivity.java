package com.example.armydocs.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;

public class DoSurveyActivity extends AppCompatActivity {

    DoSurveyActivity(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosurvey);
    }
    public void BackButton(View v){
        finish();
    }
    public void finishSurvey(View v){
        Toast.makeText(getApplicationContext(),"설문 작성을 완료했습니다.",Toast.LENGTH_LONG).show();
        finish();
    }
}
