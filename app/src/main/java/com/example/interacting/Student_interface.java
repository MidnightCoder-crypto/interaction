package com.example.interacting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Student_interface extends AppCompatActivity {

String branch="";
String sem="";
    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_interface);
        t1= findViewById(R.id.cse);
        t2= findViewById(R.id.ise);
        t3= findViewById(R.id.ece);
        t4= findViewById(R.id.eee);

        sem= getIntent().getStringExtra("SEMESTER");

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                branch="cse";
                update(branch);
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                branch="ise";
                update(branch);
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                branch="ece";
                update(branch);
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                branch="eee";
                update(branch);
            }
        });


    }

    private void update(String branch) {
        Intent i= new Intent(getApplicationContext(),Subjects_List.class);
        i.putExtra("KEY1",sem);
        i.putExtra("BRANCH",branch);
        Toast.makeText(this, ""+branch, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
