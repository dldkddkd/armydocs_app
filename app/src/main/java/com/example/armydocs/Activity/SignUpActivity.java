package com.example.armydocs.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;
import com.example.armydocs.Util.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    public EditText id;
    public int cnt;
    public long num;
    private long time= 0;
    public SignUpActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    public void signup(View v){
        //EditText i = (EditText)findViewById(R.id.new_id);
        id = (EditText)findViewById(R.id.new_id);
        EditText p = (EditText)findViewById(R.id.new_password);
        EditText n = (EditText)findViewById(R.id.new_name);
        DatePicker e = findViewById(R.id.new_enlist_date);
        DatePicker d = findViewById(R.id.new_discharge_date);
        EditText r = (EditText)findViewById(R.id.new_rank);
        EditText b = (EditText)findViewById(R.id.new_belong);


        Calendar start = Calendar.getInstance();
        Calendar finish = Calendar.getInstance();
        start.set(e.getYear(),e.getMonth(),e.getDayOfMonth());
        finish.set(d.getYear(),d.getMonth(),d.getDayOfMonth());

        if(id.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "군번을 입력하세요.", Toast.LENGTH_SHORT).show();
            id.requestFocus();
            return;
        }
        else if(p.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            p.requestFocus();
            return;
        }
        else if(b.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "소속부대를 입력하세요.", Toast.LENGTH_SHORT).show();
            b.requestFocus();
            return;
        }
        else if(n.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            n.requestFocus();
            return;
        }
        else if(r.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "계급을 입력하세요.", Toast.LENGTH_SHORT).show();
            r.requestFocus();
            return;
        }
        else if(start.getTimeInMillis() > finish.getTimeInMillis()){
            Toast.makeText(getApplicationContext(), "전역일이 입대일보다 빠릅니다.", Toast.LENGTH_SHORT).show();
            e.requestFocus();
            return;
        }
        else if(idDuplicate(id.getText().toString())){
            Toast.makeText(getApplicationContext(), "이미 가입된 군번입니다.", Toast.LENGTH_SHORT).show();
            id.requestFocus();
            return;
        }
        else{
            mRootRef.child("tb_user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    num = dataSnapshot.getChildrenCount()+1;

/*                    mRootRef.child("tb_user").setValue(num);

                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("id");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("password");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("belong");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("name");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("rank");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("enlist_day");
                    mRootRef.child("tb_user").child(String.valueOf(num)).setValue("discharge_day");*/

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });
            Intent signupIntent = new Intent(SignUpActivity.this,HomeActivity.class);
            signupIntent.putExtra("index",num);
            startActivity(signupIntent);
        }
    }

    public boolean idDuplicate(String id){

        final String ID = id;
        cnt = 0;
        mRootRef.child("tb_user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getChildrenCount() > 0){

                    for(int i=1;i<=dataSnapshot.getChildrenCount();i++) {
                        String index = String.valueOf(i);
                        String _id = dataSnapshot.child(index).child("id").getValue().toString();

                        if (_id.equals(ID)) {
                            cnt++;
                            Toast.makeText(getApplicationContext(), "이미 가입된 군번입니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
        System.out.println("cnt"+cnt);
        if(cnt >= 1)
            return true;
        else
            return false;
    }
    public void BackButton(View v){
        finish();
    }
}
