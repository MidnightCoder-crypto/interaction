package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Subjects_List extends AppCompatActivity {
String sem="";
RecyclerView rec;
String branch="";
DatabaseReference dbref;
ArrayList<String> list;
Subjects_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects__list);
        sem= getIntent().getStringExtra("KEY1");
        branch= getIntent().getStringExtra("BRANCH");
        rec= findViewById(R.id.subjects_recyclerview);
        rec.setHasFixedSize(true);

        dbref= FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects");
        list= new ArrayList<String>();

        rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    String f= dataSnapshot1.getKey();

                    list.add(f);
                }
                adapter= new Subjects_Adapter(getApplicationContext(),list,sem,branch);
                rec.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
