package com.kmu.diary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    View v;

    DB_schedule db_schedule;
    DB_todo db_todo;

    SQLiteDatabase db1;

    SQLiteDatabase db2;

    EditText editText;

    Button button;

//    TextView textView_schedule;
//    TextView textView_todo;

    ListView ListViewSchedule;
    ListView ListViewTodo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container,false);

        db_schedule = new DB_schedule(getActivity());
        db_todo = new DB_todo(getActivity());

        db1 = db_schedule.getReadableDatabase();
        db2 = db_todo.getReadableDatabase();

        ListViewSchedule = (ListView) v.findViewById(R.id.listViewSchedule);
        ListViewTodo = (ListView) v.findViewById(R.id.listViewToDo);

//        textView_schedule = (TextView) v.findViewById(R.id.textView_schedule);
//        textView_todo = (TextView) v.findViewById(R.id.textView_todo);



                Cursor cursor1;
                cursor1 = db1.rawQuery("SELECT * FROM schedule", null);

                ArrayList<String> listSchedule = new ArrayList<>();

                Cursor cursor2;
                cursor2 = db2.rawQuery("SELECT * FROM todo", null);

                ArrayList<String> listToDo = new ArrayList<>();

                cursor1.moveToFirst();
                cursor2.moveToFirst();



                while (cursor1.isAfterLast() == false) {
                    listSchedule.add(cursor1.getString(0)+". Date: "+cursor1.getString(1)+"\n    Content: "+
                            cursor1.getString(2));
                    cursor1.moveToNext();
                }

                while (cursor2.isAfterLast() == false) {
                  listToDo.add(cursor2.getString(0)+". Date: "+cursor2.getString(1)+"\n    Content: "+
                          cursor2.getString(2));
                    cursor2.moveToNext();
                }

                //create the list adapter and set the adapter
                ListAdapter adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listSchedule);
                ListViewSchedule.setAdapter(adapter1);

                ListAdapter adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listToDo);
                ListViewTodo.setAdapter(adapter2);





        return v;
    }

}
