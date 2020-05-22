package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teachers_List extends AppCompatActivity{
String sem= "";
String branch="";
String sub="";
String teacher_id="";
DatabaseReference dbref,teacher_ref,compare_ref,final_ref;
RecyclerView recyclerView;
ArrayList<List_Of_Teachers_Class> list;
List_Of_Teachers_Class list_of_teachers_class;
String uid="";
List_Of_Teachers_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_list);
        recyclerView= findViewById(R.id.notes_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<List_Of_Teachers_Class>();
        Intent i = getIntent();

        sem = i.getStringExtra("SEMESTER");
        branch = i.getStringExtra("BRANCH");
        sub = i.getStringExtra("SUBJECT");
        list_of_teachers_class= new List_Of_Teachers_Class();



        dbref = FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub);
        teacher_ref= FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub).child("teachers");
        teacher_ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teacher_id= teacher_ref.getKey();
                validate(teacher_id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    private void validate(final String teacher_id) {


        compare_ref= FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(branch).child("subjects").child(sub).child("teachers");
        compare_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {

                    list_of_teachers_class= dataSnapshot1.getValue(List_Of_Teachers_Class.class);

                    list.add(list_of_teachers_class);


                }

                adapter= new List_Of_Teachers_Adapter(getApplicationContext(),list,sem,branch,sub,teacher_id);
                recyclerView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }


}
