package com.kmu.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_schedule extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyMovies.db";
    public static final String SCHEDULE_TABLE_NAME = "schedule";
    public static final String SCHEDULE_COLUMN_ID = "id";
    public static final String SCHEDULE_COLUMN_DATE = "date";
    public static final String SCHEDULE_COLUMN_CONTENT = "content";

    public DB_schedule(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table schedule " +
                        "(id integer primary key,date text, content text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS schedule");
        onCreate(db);
    }

    public boolean insertMovie(String date, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("date", date);
        contentValues.put("content", content);

        db.insert("schedule", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from schedule where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SCHEDULE_TABLE_NAME);
        return numRows;
    }

    public boolean updateMovie(Integer id, String date, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("content", content);
        db.update("schedule", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMovie(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("schedule",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllMovies() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from schedule", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(SCHEDULE_COLUMN_ID))+". "+res.getString(res.getColumnIndex(SCHEDULE_COLUMN_DATE))+": "+
                    res.getString(res.getColumnIndex(SCHEDULE_COLUMN_CONTENT)));
            res.moveToNext();
        }
        return array_list;
    }
}





