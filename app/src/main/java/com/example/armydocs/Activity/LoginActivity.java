package com.example.armydocs.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference mRootRef;
    EditText id;
    EditText pw;

    public LoginActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv = (TextView)findViewById(R.id.findIdPw);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디,비번 찾기
                int a = 0;
            }
        });
    }
    public void MoveToSignUp(View v){
        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }
    public void login(View v){
        id = findViewById(R.id.id);
        pw = findViewById(R.id.password);

        //디버그용. 나중에 주석처리 할 것
        //startActivity(new Intent(LoginActivity.this,HomeActivity.class));

        if(id.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "아이디를 입력하세요.", Toast.LENGTH_LONG).show();
            id.requestFocus();
            return;
        }
        else if(pw.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
            pw.requestFocus();
            return;
        }
        else{
            System.out.println("1");
            mRootRef = FirebaseDatabase.getInstance().getReference();
            mRootRef.child("tb_user").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    System.out.println("2");
                    if(dataSnapshot.getChildrenCount() > 0){

                        for(int i=1;i<=dataSnapshot.getChildrenCount();i++) {
                            String index = String.valueOf(i);
                            String _id = dataSnapshot.child(index).child("id").getValue().toString();
                            String _pw = dataSnapshot.child(index).child("password").getValue().toString();

                            System.out.println(!_id.equals(id.getText().toString()));
                            System.out.println(!_pw.equals(pw.getText().toString()));

                            if (_id.equals(id.getText().toString()) && _pw.equals(pw.getText().toString())) {
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                intent.putExtra("index",index);
                                startActivity(intent);
                                pw.setText("");
                                id.setText("");
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("!");
                }
            });
            System.out.println("3");
        }
    }
}
