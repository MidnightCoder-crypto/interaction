package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachers_Interface extends AppCompatActivity {
Spinner spinner_sem, spinner_sub,spinner_dept;
String[] list_semesters;
String[] list_departments;
String sem="";
String sub="";
String dept="";
RecyclerView rec;
ArrayList<String> list;
list_of_subjects_adapter adapter;
Button btn;
DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers__interface);
        spinner_sem= findViewById(R.id.spin_sem);
        spinner_dept= findViewById(R.id.spin_dept);
        btn= findViewById(R.id.next);






        list_departments= getResources().getStringArray(R.array.department);
        list_semesters= getResources().getStringArray(R.array.semesters);
        list= new ArrayList<String>();
        ArrayAdapter<String> adapter_dept= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_departments);
        ArrayAdapter<String> adapter_semesters= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list_semesters);

        adapter_dept.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter_semesters.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner_dept.setAdapter(adapter_dept);
        spinner_sem.setAdapter(adapter_semesters);
//        rec= findViewById(R.id.list_of_subjects_recyclerview);
//        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        rec.setHasFixedSize(true);

        spinner_dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dept=list_departments[i];

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner_sem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sem= list_semesters[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),name_of_list_of_subjects.class);
                i.putExtra("KEY",sem);
                i.putExtra("DEPT",dept);
                startActivity(i);
            }
        });






    }
}
