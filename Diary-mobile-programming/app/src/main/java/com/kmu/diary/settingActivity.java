package com.kmu.diary;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;  //버전 맞춰서 import

public class settingActivity extends AppCompatActivity {

    SwitchCompat switch_1, switch_2;
    boolean stateSwitch1, stateSwitch2;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //뒤로가기 버튼 추

        preferences = getSharedPreferences("PREFS", 0);
        stateSwitch1 = preferences.getBoolean("switch1", true);
        stateSwitch2 = preferences.getBoolean("switch2", true);


        switch_1 = (SwitchCompat) findViewById(R.id.switch_1);
        switch_2 = (SwitchCompat) findViewById(R.id.switch_2);

        switch_1.setChecked(stateSwitch1);
        switch_2.setChecked(stateSwitch2);

        switch_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stateSwitch1 = !stateSwitch1;
                switch_1.setChecked(stateSwitch1); // 클릭 되었으니까 반대로
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch1", stateSwitch1);
                editor.apply();
            }
        });
        switch_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stateSwitch2 = !stateSwitch2;
                switch_2.setChecked(stateSwitch2);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch2", stateSwitch2);
                editor.apply();
            }
        });
    }

    // 뒤로가기 버튼 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
