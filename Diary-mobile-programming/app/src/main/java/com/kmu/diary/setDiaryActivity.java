package com.kmu.diary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.database.Cursor;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class setDiaryActivity extends AppCompatActivity {

    private DB_diary mydb;
    TextView title;
    TextView date;
    TextView content;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_diary);


        title = (TextView) findViewById(R.id.editTextTitle) ;
        date = (TextView) findViewById(R.id.editTextDate);
        content = (TextView) findViewById(R.id.editTextContent);

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
                String t = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_TITLE));
                String d = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_DATE));
                String c = rs.getString(rs.getColumnIndex(DB_diary.DIARY_COLUMN_CONTENT));
                if (!rs.isClosed()) {
                    rs.close();
                }

                Button b = (Button) findViewById(R.id.button);
                b.setVisibility(View.INVISIBLE);

                title.setText((CharSequence) t);
                date.setText((CharSequence) d);
                content.setText((CharSequence) c);
            }
        }
    }

    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateMovie(id, title.getText().toString(), date.getText().toString(), content.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), kr.co.company.moviedatabase.MainActivity.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertMovie(title.getText().toString(), date.getText().toString(), content.getText().toString())) {
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
                if (mydb.updateMovie(id, title.getText().toString(), date.getText().toString(), content.getText().toString())) {
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
