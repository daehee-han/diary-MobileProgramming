package com.kmu.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;  //버전 맞춰서 import
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.kmu.diary.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    Fragment selectedFragment = new homeFragment();
    DrawerLayout side_drawer;
    BottomNavigationView bottom_navigation;

    NavigationView side_navigation;

    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        side_drawer = (DrawerLayout) findViewById(R.id.main_drawer);

        // 메뉴 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        toggle = new ActionBarDrawerToggle(this,side_drawer,R.string.open, R.string.close);
        side_drawer.addDrawerListener(toggle);




        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();



        bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavSelectedListener);

        side_navigation = (NavigationView) findViewById(R.id.side_navigation);
        side_navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_schedule:
                selectedFragment = new scheduleFragment();
                break;
            case R.id.navigation_todo:
                selectedFragment = new todoFragment();
                break;
            case R.id.navigation_diary:
                selectedFragment = new diaryFragment();
                break;
            case R.id.navigation_memo:
                selectedFragment = new memoFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        side_drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.setting,menu);

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.action_setting){
            // User chose the "Settings" item, show the app settings UI...
            Intent intent = new Intent(getApplicationContext(), settingActivity.class);
            startActivity(intent);
            return true;
        }
        else if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        else{
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);

        }
    }

    // bottom_navigation selected 이벤트를 처리해주는 함수
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // 화면에 글자 띄워줌 (하단바 말고 주화면에)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.navigation_calendar:
                    selectedFragment = new calendarFragment();
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
