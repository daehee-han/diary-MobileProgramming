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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class scheduleFragment extends Fragment {

    private static final String TAG = "scheduleFragment";
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    View v;


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////
////        View v = infl
////        setContentView(R.layout.fragment_schedule);
////        editText = (EditText) findViewById(R.id.editText);
////        btnAdd = (Button) findViewById(R.id.btnAdd);
////        btnViewData = (Button) findViewById(R.id.btnView);
////        mDatabaseHelper = new DatabaseHelper(this);
//
////        btnAdd.setOnClickListener(new View.OnClickListener(){
////            @Override
////            public void onClick(View v) {
////
////                String newEntry = editText.getText().toString();
////                if(editText.length() != 0){
////                    AddData(newEntry);
////                    editText.setText("");
////                }else{
////                    Toast.makeText(getActivity(),"You must put somthing in the text field!", Toast.LENGTH_SHORT).show();
////
////                }
////            }
////        });
////
////        btnViewData.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(), ListDataActivity.class);
////                startActivity(intent);
////            }
////        });
//
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_schedule ,container,false);

        //setContentView(R.layout.fragment_schedule);

        editText = (EditText) v.findViewById(R.id.editText);
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnViewData = (Button) v.findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(getActivity());

//        SharedPreferences setting = this.getActivity().getSharedPreferences("PREFS", 0);
//        editText.setText(setting.getString("value",""));

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String newEntry = editText.getText().toString();
                if(editText.length() != 0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    Toast.makeText(getActivity(),"You must put somthing in the text field!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ListDataActivity.class);
                startActivity(intent);

            }
        });

        return v;

    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            Toast.makeText(getActivity(),"Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Someting went wrong", Toast.LENGTH_SHORT).show();

        }
    }





}
