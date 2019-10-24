package com.example.armydocs.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.Class.Question;
import com.example.armydocs.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WriteSurveyActivity extends CreateSurveyActivity{
    public static Activity createSurveyActivity;
    public int question = 0;
    public LayoutInflater mInflater;
    public LinearLayout mRootLinear;
    public View v;
    public Intent intent;
    public RadioButton ws_short;
    public RadioButton ws_long;
    public String ws_survey_title;
    public String ws_survey_intro;
    public String ws_survey_start;
    public String ws_survey_finish;
    public Question qs;

    public WriteSurveyActivity() {
        qs = new Question();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writesurvey);

        intent = getIntent();
        ws_short = findViewById(R.id.ws_short);
        ws_long = findViewById(R.id.ws_long);
        ws_survey_title = "";
        ws_survey_intro = "";
        ws_survey_start = "";
        ws_survey_finish = "";

        //Toast.makeText(getApplicationContext(),"시"+intent.getExtras().get("cs_survey_title").toString(),Toast.LENGTH_LONG).show();

        if(intent.getExtras().get("cs_survey_title") != null)
            ws_survey_title = intent.getExtras().get("cs_survey_title").toString();
        if(intent.getExtras().get("cs_survey_intro") != null)
            ws_survey_title = intent.getExtras().get("cs_survey_intro").toString();
        if(intent.getExtras().get("cs_survey_start") != null)
            ws_survey_title = intent.getExtras().get("cs_survey_start").toString();
        if(intent.getExtras().get("cs_survey_finish") != null)
            ws_survey_title = intent.getExtras().get("cs_survey_finish").toString();

        mRootLinear  = (LinearLayout)findViewById(R.id.linear_root);
        mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);;
        v = mInflater.inflate(R.layout.activity_question_short,mRootLinear,true);

        ImageView backbutton = (ImageView)findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            setResult(0);
            finish();
            }
        });

        TextView index = findViewById(R.id.question_index);
        index.setText(question+1+".");

        TextView qb = (TextView)findViewById(R.id.ws_aq);
        TextView mq = (TextView)findViewById(R.id.ws_mq);

        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
	            question++;
                CheckBox cb = new CheckBox(WriteSurveyActivity.this);
                EditText et = new EditText(WriteSurveyActivity.this);
                View v;
                if(ws_short.isChecked()) {
                    v = mInflater.inflate(R.layout.activity_question_short, null);
                    v.setId(question);

                    mRootLinear.addView(v);
                }
                else if(ws_long.isChecked()) {
                    v = mInflater.inflate(R.layout.activity_question_long, null);
                    v.setId(question);

                    mRootLinear.addView(v);
                }
            }
        });
        mq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
	            View v = (View)findViewById(question);
	            if(v != null) {
                    ViewGroup parentViewGroup = (ViewGroup) v.getParent();
                    parentViewGroup.removeView(v);

                    if (question > 1)
                        question--;
                }
            }
        });

    }
    public void plus_option(View v){
        v.setVisibility(View.GONE);
        View view = mInflater.inflate(R.layout.activity_option, null);
        mRootLinear.addView(view);
    }
    public void delete_option(View v){
        ViewGroup vg = (ViewGroup)v.getParent();
        vg.setVisibility(View.GONE);
    }
    public void BackButton(View v){
        finish();
    }
    public void save(View v){
        ((HomeActivity)HomeActivity.mContext).setVisible(v);
        Toast.makeText(getApplicationContext(),"설문조사가 등록되었습니다.",Toast.LENGTH_LONG).show();
        finish();
    }
}