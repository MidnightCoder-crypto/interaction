package com.example.interacting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class Subjects_Adapter extends RecyclerView.Adapter<Subjects_Adapter.MyViewHolder> {
Context c;
    ArrayList<String> list;
    String sem;
    String branch;





    public Subjects_Adapter(Context c,ArrayList<String> l,String sem,String branch) {
        this.c=c;
        list=l;
        this.sem=sem;
        this.branch=branch;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_subjects,parent,false));



    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.txt.setText(list.get(position));
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(c, Teachers_List.class);
                i.putExtra("SEMESTER",sem);
                i.putExtra("BRANCH",branch);
                i.putExtra("SUBJECT",holder.txt.getText().toString());
                c.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt;

        public MyViewHolder(final View itemView) {
            super(itemView);
            txt= itemView.findViewById(R.id.txt_subjects);



        }


    }




}
