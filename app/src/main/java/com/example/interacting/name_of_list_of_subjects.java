package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class name_of_list_of_subjects extends AppCompatActivity {
RecyclerView rec;
ArrayList<String> list;
list_of_subjects_adapter adapter;
DatabaseReference dbref;
String sem= "";
String dept="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_of_list_of_subjects);
        rec= findViewById(R.id.list_of_subjects_recycler);
        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rec.setHasFixedSize(true);
        sem= getIntent().getStringExtra("KEY");
        dept= getIntent().getStringExtra("DEPT");



    dbref = FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(dept).child("subjects");

list= new ArrayList<String>();

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    String f= dataSnapshot1.getKey();
                    list.add(f);
                }
                adapter= new list_of_subjects_adapter(getApplicationContext(),list,sem,dept);
                rec.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
