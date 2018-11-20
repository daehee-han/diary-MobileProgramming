package com.kmu.diary;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;  //버전 맞춰서 import
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.kmu.diary.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                if(password.equals("")){
                    // there is no password
                    Intent mainIntent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else{
                    // there is a password
                    Intent mainIntent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                    startActivity(mainIntent);
                    finish();

                }


            }

        },SPLASH_TIME_OUT);
    }
}
