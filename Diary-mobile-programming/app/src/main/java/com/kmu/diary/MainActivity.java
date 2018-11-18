package com.kmu.diary;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(bottomNavSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // 화면에 글자 띄워줌 (하단바 말고 주화면에)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_calendar:
                    selectedFragment = new calendarFragment();
                    break;
                case R.id.navigation_home:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.navigation_search:
                    selectedFragment = new searchFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };


}
