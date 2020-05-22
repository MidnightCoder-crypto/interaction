package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
String uid="";
DatabaseReference dbref;
TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
String sem="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbref= FirebaseDatabase.getInstance().getReference().child("LoginTeacher");
//        semref= FirebaseDatabase.getInstance().getReference().child("Material");

        t1= findViewById(R.id.sem1);
        t2= findViewById(R.id.sem2);
        t3= findViewById(R.id.sem3);
        t4= findViewById(R.id.sem4);
        t5= findViewById(R.id.sem5);
        t6= findViewById(R.id.sem6);
        t7= findViewById(R.id.sem7);
        t8= findViewById(R.id.sem8);
        t9= findViewById(R.id.sem9);
        t10= findViewById(R.id.sem10);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem1";
                update(sem);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem2";
                update(sem);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem3";
                update(sem);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem4";
                update(sem);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem5";
                update(sem);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem6";
                update(sem);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem7";
                update(sem);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem8";
                update(sem);
            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem9";
                update(sem);
            }
        });
        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sem= "sem10";
                update(sem);
            }
        });




    }

    private void update(String sem) {
        Intent i= new Intent(getApplicationContext(),Student_interface.class);
        i.putExtra("SEMESTER",sem);
        Toast.makeText(this, ""+sem, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.teacher_login: verify();
                                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void verify() {
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    uid= dataSnapshot1.getKey();
                    if(uid.equals("A0X3pT1GASNTabN5qAhoyRge1bz2"))
                    {
                        startActivity(new Intent(getApplicationContext(),Teachers_Interface.class));
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

