package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_of_Notes extends AppCompatActivity{
    String sem= "";
    String branch="";
    String sub="";
    String teacher_id="";
    DatabaseReference dbref,teacher_ref,compare_ref,final_ref;
    RecyclerView recyclerView;
    ArrayList<Notes> list;
    Notes notes;

    NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__notes);
        recyclerView= findViewById(R.id.study_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent i = getIntent();

        sem = i.getStringExtra("KEY");
        branch = i.getStringExtra("BRANCH");
        sub = i.getStringExtra("SUBJECT");
        teacher_id= i.getStringExtra("TEACHER_ID");

        notes = new Notes();



        dbref = FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub).child(teacher_id);
//        dbref.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
//                {
//                    String n;
//                    n= dataSnapshot1.getKey();
////                    Toast.makeText(List_of_Notes.this, ""+n, Toast.LENGTH_SHORT).show();
//                    validate(n);
//
//                }
//
////                validate(teacher_id);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String x = dataSnapshot.getKey();
                Toast.makeText(List_of_Notes.this, ""+x, Toast.LENGTH_SHORT).show();
                validate(x);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










    }

    private void validate(String x) {

//        Toast.makeText(this, ""+teacher_id, Toast.LENGTH_SHORT).show();

        compare_ref= FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub).child(x);
        final_ref=  FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub);
//        Toast.makeText(this, "ID: "+x, Toast.LENGTH_SHORT).show();




        compare_ref.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list.clear();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        adapter= new NotesAdapter(getApplicationContext(),list);
//        recyclerView.setAdapter(adapter);


    }


}

