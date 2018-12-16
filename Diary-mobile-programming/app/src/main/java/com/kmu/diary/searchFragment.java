package com.kmu.diary;

        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class searchFragment extends Fragment {

    View v;

    DB_schedule db_schedule;
    DB_todo db_todo;

    SQLiteDatabase db1;

    SQLiteDatabase db2;

    EditText editText;

    Button button;

    TextView textView_schedule;
    TextView textView_todo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_search, container,false);

        db_schedule = new DB_schedule(getActivity());
        db_todo = new DB_todo(getActivity());

        db1 = db_schedule.getReadableDatabase();
        db2 = db_todo.getReadableDatabase();

        editText = (EditText) v.findViewById(R.id.editText);
        button = (Button) v.findViewById(R.id.button);

        textView_schedule = (TextView) v.findViewById(R.id.textView_schedule);
        textView_todo = (TextView) v.findViewById(R.id.textView_todo);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String date = editText.getText().toString();

                Cursor cursor1;
                cursor1 = db1.rawQuery("SELECT date, content FROM schedule WHERE date='"
                        + date + "';", null);

                Cursor cursor2;
                cursor2 = db2.rawQuery("SELECT date, content FROM todo WHERE date='"
                        + date + "';", null);

                while (cursor1.moveToNext()) {
                    textView_schedule.setText("");
                    String content1 = cursor1.getString(1);
                    textView_schedule.setText(content1);
                }

                while (cursor2.moveToNext()) {
                    textView_todo.setText("");
                    String content2 = cursor2.getString(1);
                    textView_todo.setText(content2);
                }


            }
        });


        return v;
    }

//    public void search(View v) {
//        String date = editText.getText().toString();
//
//        Cursor cursor1;
//        cursor1 = db1.rawQuery("SELECT date, content FROM schedule WHERE date='"
//                + date + "';", null);
//
//        Cursor cursor2;
//        cursor2 = db1.rawQuery("SELECT date, content FROM todo WHERE date='"
//                + date + "';", null);
//
//        while (cursor1.moveToNext()) {
//            String content1 = cursor1.getString(1);
//            textView_schedule.setText(content1);
//        }
//
//        while (cursor2.moveToNext()) {
//            String content2 = cursor2.getString(1);
//            textView_schedule.setText(content2);
//        }
//    }
}
