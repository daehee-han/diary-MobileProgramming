package com.kmu.diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class todoFragment extends Fragment {

    private ListView myListView;
    DB_todo mydb;
    ArrayAdapter mAdapter;
    Button button;
    View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_todo ,container,false);

        mydb = new DB_todo(getActivity());
        ArrayList array_list = mydb.getAllMovies();

        mAdapter =
                new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, array_list);

        myListView = (ListView) v.findViewById(R.id.listView1);
        myListView.setAdapter(mAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long arg4) {


                String item = (String) ((ListView) parent).getItemAtPosition(position);
                String[] strArray = item.split(". ");
                int id=Integer.parseInt(strArray[0]);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id);
                Intent intent = new Intent(getActivity(), com.kmu.diary.setToDoActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        button = v.findViewById(R.id.btnAdd);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", 0);
                Intent intent = new Intent(getActivity(), com.kmu.diary.setToDoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.clear();
        mAdapter.addAll(mydb.getAllMovies());
        mAdapter.notifyDataSetChanged();
    }

}
