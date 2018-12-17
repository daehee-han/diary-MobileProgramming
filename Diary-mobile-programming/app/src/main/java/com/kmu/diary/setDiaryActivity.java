package com.kmu.diary;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class setDiaryActivity extends AppCompatActivity {

    private DB_diary mydb;

    TextView date;
    TextView content;
    TextView location;

    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diary);

        date = (TextView) findViewById(R.id.editTextDate);
        content = (TextView) findViewById(R.id.editTextContent);
        location = (TextView) findViewById(R.id.editTextLocation);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.diary_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mydb = new DB_diary(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id = Value;
                rs.moveToFirst();
                String d = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_DATE));
                String c = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_CONTENT));
                String l = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_LOCATION));
                if (!rs.isClosed()) {
                    rs.close();
                }

                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                date.setText((CharSequence) d);
                content.setText((CharSequence) c);
                location.setText((CharSequence) l);
            }
        }
    }

    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateMovie(id, date.getText().toString(), content.getText().toString(), location.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), kr.co.company.moviedatabase.MainActivity.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertMovie(date.getText().toString(), content.getText().toString(), location.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                mydb.deleteMovie(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void edit(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("id");
            if (value > 0) {
                if (mydb.updateMovie(id, date.getText().toString(), content.getText().toString(), location.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
        }
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
