package com.kmu.diary;

import android.content.Intent;
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

public class memoFragment extends Fragment {

    private static final String TAG = "memoFragment";
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    View v;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_memo ,container,false);

        editText = (EditText) v.findViewById(R.id.editText);
        btnAdd = (Button) v.findViewById(R.id.btnAdd);
        btnViewData = (Button) v.findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(getActivity());



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
