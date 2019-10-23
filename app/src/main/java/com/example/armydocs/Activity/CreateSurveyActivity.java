package com.example.armydocs.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.armydocs.R;
import com.example.armydocs.Class.Survey;
import com.example.armydocs.Util.DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class CreateSurveyActivity extends AppCompatActivity {
    public static ArrayList<Activity> actList = new ArrayList<Activity>();
    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;
    private File tempFile;
    public static Activity saveActivity;
    Survey survey = new Survey();
    EditText cs_survey_title;
    TextView cs_next;
    TextView cs_cancel;
    DatePicker cs_start_day;
    DatePicker cs_finish_day;
    EditText cs_survey_intro;
    ImageView cs_gallary;
    CheckBox cs_agree;


    public CreateSurveyActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        actList.add(this);
        saveActivity = this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_createsurvey);

        cs_gallary = (ImageView) findViewById(R.id.cs_gallary);
        cs_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_ALBUM);
            }
        });

        cs_survey_title = findViewById(R.id.cs_survey_title);

        cs_start_day = findViewById(R.id.cs_start_day);
        cs_finish_day = findViewById(R.id.cs_finish_day);
        cs_survey_intro = findViewById(R.id.cs_survey_intro);
        cs_next = findViewById(R.id.cs_next_btn);
        cs_cancel = findViewById(R.id.cs_cancel_btn);
        cs_agree = findViewById(R.id.cs_agree);

        cs_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Calendar start = Calendar.getInstance();
            Calendar finish = Calendar.getInstance();
            start.set(cs_start_day.getYear(),cs_start_day.getMonth(),cs_start_day.getDayOfMonth());
            finish.set(cs_finish_day.getYear(),cs_finish_day.getMonth(),cs_finish_day.getDayOfMonth());

            if(cs_survey_title.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(),"제목을 입력하세요.",Toast.LENGTH_SHORT).show();
                return;
            }

            if(start.getTimeInMillis() > finish.getTimeInMillis()){
                Toast.makeText(getApplicationContext(),"시작일은 종료일 전 날짜로 설정하세요.",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!cs_agree.isChecked()){
                Toast.makeText(getApplicationContext(),"개인정보 이용에 동의하세요.",Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CreateSurveyActivity.this, WriteSurveyActivity.class);
            intent.putExtra("cs_survey_title",cs_survey_title.toString());
            intent.putExtra("cs_start_day",cs_start_day.toString());
            intent.putExtra("cs_finish_day",cs_finish_day.toString());
            intent.putExtra("cs_survey_intro",cs_survey_intro.toString());

            startActivity(intent);
            finish();
            }
        });

        cs_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == PICK_FROM_ALBUM) {
            if(data == null)
                return;
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
        else if(requestCode == 3){
            finish();
        }
    }
    private void setImage() {

        cs_gallary = (ImageView) findViewById(R.id.cs_gallary);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);

        cs_gallary.setImageBitmap(originalBm);

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

    private void valueCheck(Calendar start, Calendar finish, EditText _cs_survey_title){
        if(_cs_survey_title.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"제목을 입력하세요.",Toast.LENGTH_LONG);
            return;
        }
        if(start.getTimeInMillis() > finish.getTimeInMillis()){
            Toast.makeText(getApplicationContext(),"시작일은 종료일 전 날짜로 설정하세요.",Toast.LENGTH_LONG);
            return;
        }
    }
    public void BackButton(View v){
        finish();
    }

    public void actFinish(){
        for(int i = 0; i < actList.size(); i++)
            actList.get(i).finish();
    }
}