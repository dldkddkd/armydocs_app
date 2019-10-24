package com.example.armydocs.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;

public class IntroduceActivity extends AppCompatActivity {

    IntroduceActivity(){ }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_dev);

        TextView survey_result = findViewById(R.id.survey_result);
        survey_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroduceActivity.this,ResultActivity.class));
            }
        });

        ImageView profile = findViewById(R.id.gotoInfo);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(IntroduceActivity.this,InfoActivity.class);

                startActivity(infoIntent);
            }
        });
    }
    public void BackButton(View v){
        finish();
    }
}
