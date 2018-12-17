package com.kmu.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DB_diary extends SQLiteOpenHelper {

    public static final String DIARY_TABLE_NAME = "diary";
    public static final String DIARY_COLUMN_ID = "id";
    public static final String DIARY_COLUMN_TITLE = "title";
    public static final String DIARY_COLUMN_DATE = "date";
    public static final String DIARY_COLUMN_CONTENT = "content";

    public DB_diary(Context context) {
        super(context, DIARY_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table diary " +
                        "(id integer primary key,title text, date text, content text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public boolean insertMovie(String title, String date, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("content", content);

        db.insert("diary", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diary where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DIARY_TABLE_NAME);
        return numRows;
    }

    public boolean updateMovie(Integer id, String title, String date, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("date", date);
        contentValues.put("content", content);
        db.update("diary", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMovie(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("diary",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllMovies() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from diary", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(DIARY_COLUMN_ID))+". "+res.getString(res.getColumnIndex(DIARY_COLUMN_DATE))+res.getString(res.getColumnIndex(DIARY_COLUMN_DATE))+": "+
                    res.getString(res.getColumnIndex(DIARY_COLUMN_CONTENT)));
            res.moveToNext();
        }
        return array_list;
    }
}





