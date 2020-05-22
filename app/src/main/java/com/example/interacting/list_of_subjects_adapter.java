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

public class list_of_subjects_adapter extends RecyclerView.Adapter<list_of_subjects_adapter.MyViewHolder> {
    Context c;
    ArrayList<String> list;
    String sem;
    String branch;





    public list_of_subjects_adapter(Context c,ArrayList<String> l,String sem,String branch) {
        this.c=c;
        list=l;
        this.sem=sem;
        this.branch=branch;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_list_of_subjects,parent,false));



    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.txt.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(c,Uploading_Interface.class);
                i.putExtra("KEY",sem);
                i.putExtra("DEPT",branch);
                i.putExtra("SUBJECT_NAME",holder.txt.getText().toString());
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
            txt= itemView.findViewById(R.id.txt_subjects_list);



        }


    }




}
