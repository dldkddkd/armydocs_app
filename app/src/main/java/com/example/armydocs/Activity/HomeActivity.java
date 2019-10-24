package com.example.armydocs.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    public TextView profile_rank;
    public TextView profile_name;
    public TextView profile_enlist;
    public TextView profile_discharge;
    public Intent intent;
    public String id;
    public static Context mContext;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    String [] list = {"1","2","3","4","5","6","7"};

    public HomeActivity() {

    }

    private void Initialize(){
        mContext = this;
        mRootRef.child("tb_user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                intent = getIntent();
                String index = intent.getExtras().get("index").toString();
                if (dataSnapshot.getChildrenCount() > 0 && index != null) {
                    String _name = dataSnapshot.child(index).child("name").getValue().toString();
                    String _rank = dataSnapshot.child(index).child("rank").getValue().toString();
                    String _enlist = dataSnapshot.child(index).child("enlist_day").getValue().toString();
                    String _discharge = dataSnapshot.child(index).child("discharge_day").getValue().toString();

                    profile_rank = findViewById(R.id.profile_rank);
                    profile_name = findViewById(R.id.profile_name);
                    profile_enlist = findViewById(R.id.profile_enlist_day);
                    profile_discharge = findViewById(R.id.profile_discharge_day);

                    if (profile_rank != null)
                        profile_rank.setText(_rank);
                    if (profile_rank != null)
                        profile_name.setText(_name);
                    if (profile_rank != null)
                        profile_enlist.setText(_enlist);
                    if (profile_rank != null)
                        profile_discharge.setText(_discharge);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Initialize();

        final ArrayList<String> items = new ArrayList<String>() ;
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, items) ;

        mRootRef.child("tb_survey").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() > 0) {

                    for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                        String index = String.valueOf(i);
                        String survey_title = dataSnapshot.child(index).child("survey_title").getValue().toString();

                        items.add(survey_title);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        TextView faq = findViewById(R.id.FAQ);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문의하기 기능
            }
        });
        TextView intro_dev = findViewById(R.id.introduce_dev);
        intro_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,IntroduceActivity.class));
            }
        });
        TextView survey_result = findViewById(R.id.survey_result);
        survey_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ResultActivity.class));
            }
        });

        ImageView profile = findViewById(R.id.gotoInfo);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(HomeActivity.this,InfoActivity.class);

                startActivity(infoIntent);
            }
        });

        LinearLayout l1 = findViewById(R.id.s1);
        if(l1.getVisibility() != View.INVISIBLE){
            l1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.activity_dosurvey);
                }
            });
        }

        LinearLayout l2 = findViewById(R.id.s2);
        if(l2.getVisibility() != View.INVISIBLE){
            l2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.activity_dosurvey2);
                }
            });
        }

        LinearLayout l3 = findViewById(R.id.s3);
        if(l3.getVisibility() != View.INVISIBLE){
            l3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setContentView(R.layout.activity_dosurvey3);
                }
            });
        }
    }
    public void clickForSurvey(View v){
        startActivity(new Intent(HomeActivity.this, CreateSurveyActivity.class));
    }

    public void logout(View v){
        Toast.makeText(getApplicationContext(),"로그아웃을 실행했습니다",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void doSurvey(View v){
        startActivity(new Intent(HomeActivity.this, AnswerActivity.class));
    }
    public void setVisible(View v){
        LinearLayout ll = findViewById(R.id.s3);
        ll.setVisibility(View.VISIBLE);
    }
    public void BackButton(View v){
        setContentView(R.layout.activity_home);
    }
}