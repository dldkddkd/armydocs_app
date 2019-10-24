package com.example.armydocs.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.armydocs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {
    public static Activity infoActivity;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    private File tempFile;
    public Intent infointent;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    public InfoActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        infoActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mRootRef.child("tb_user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                infointent = getIntent();
                String index = infointent.getExtras().get("index").toString();
                if (dataSnapshot.getChildrenCount() > 0 && index != null) {
                    String _name = dataSnapshot.child(index).child("name").getValue().toString();
                    String _rank = dataSnapshot.child(index).child("rank").getValue().toString();
                    String _belong = dataSnapshot.child(index).child("rank").getValue().toString();

                    EditText profile_rank = findViewById(R.id.info_rank);
                    EditText profile_name = findViewById(R.id.info_name);
                    EditText profile_belong = findViewById(R.id.info_belong);

                    if (profile_rank != null)
                        profile_rank.setText(_rank);
                    if (profile_name != null)
                        profile_name.setText(_name);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        final ImageView backbutton = (ImageView) findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoActivity.finish();
            }
        });

        ImageView gallary = (ImageView) findViewById(R.id.gallary);
        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });

        Button save = (Button)findViewById(R.id.info_save);
        final DatePicker info_enlist_day = findViewById(R.id.info_enlist_day);
        final DatePicker info_discharge_day = findViewById(R.id.info_discharge_day);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db 등록하기

                EditText info_name = findViewById(R.id.info_name);
                EditText info_rank = findViewById(R.id.info_rank);
                EditText info_belong = findViewById(R.id.info_belong);

                Calendar start = Calendar.getInstance();
                Calendar finish = Calendar.getInstance();
                start.set(info_enlist_day.getYear(), info_enlist_day.getMonth(), info_enlist_day.getDayOfMonth());
                finish.set(info_discharge_day.getYear(), info_discharge_day.getMonth(), info_discharge_day.getDayOfMonth());

                if (info_name.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    ;
                    return;
                }
                if (info_rank.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "계급을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (info_belong.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "소속 부대를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (start.getTimeInMillis() > finish.getTimeInMillis()) {
                    Toast.makeText(getApplicationContext(), "전역일이 입대일보다 빠릅니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                finish();
                Toast.makeText(getApplicationContext(), "개인정보가 수정되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();
            Cursor cursor = null;
            try {
                String[] proj = {MediaStore.Images.Media.DATA};

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();
                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                if(tempFile != null) {
                    if (tempFile.exists()) {
                        if (tempFile.delete()) {
                            tempFile = null;
                        }
                    }
                }
                return;
            }
            else if (requestCode == PICK_FROM_ALBUM) {
                photoUri = data.getData();
                setImage();
            } else if (requestCode == PICK_FROM_CAMERA) {
                setImage();
            }
        }
    }
    private void setImage() {

        ImageView gallary = (ImageView) findViewById(R.id.gallary);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        gallary.setImageBitmap(originalBm);

    }
    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            tempFile = createImageFile();
        } catch (IOException e) {
            Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
        if (tempFile != null) {
            Uri photoUri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
    }
    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "blackJin_" + timeStamp + "_";

        File storageDir = new File(Environment.getExternalStorageDirectory() + "/blackJin/");
        if (!storageDir.exists()) storageDir.mkdirs();

        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        return image;
    }

}