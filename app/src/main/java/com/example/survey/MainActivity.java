package com.example.survey;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.example.survey.VariablesManager;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private AppBarConfiguration mAppBarConfiguration;
    public VariablesManager variables = VariablesManager.getInstance();
    ViewFlipper flipper;
    List<ImageView> indexes;


    private void CheckFirstTime()
    {
        SharedPreferences   pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean           first = false;//pref.getBoolean("isFirst", false);
        System.out.println("bool:"+first);
        if (first == false)
        {
            variables.setTutorial(true);
            SharedPreferences.Editor    editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
            // 앱 최초 실행시 수행할 작업
        }
        else
        {
            Log.d("Is first Time?", "not first");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)  {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckFirstTime();

        SharedPreferences preference = getSharedPreferences("a", MODE_PRIVATE);
        System.out.println("tutor:"+variables.getTutorial());
        Toast.makeText(this.getApplicationContext(),"tutor:"+variables.getTutorial(),Toast.LENGTH_SHORT);
        if(variables.getTutorial()){
            Intent intent = new Intent(MainActivity.this, FirstStartActivity.class);
            startActivity(intent);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void gotoMain(){
        variables.setTutorial(false);
        Toast.makeText(this.getApplicationContext(),"bye",Toast.LENGTH_SHORT);
        onBackPressed();
    }

    @Override
    public void onBackPressed(){

        finish();
    }
}