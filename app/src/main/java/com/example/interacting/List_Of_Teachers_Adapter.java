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

public class List_Of_Teachers_Adapter extends RecyclerView.Adapter<List_Of_Teachers_Adapter.MyViewHolder> {
    Context context;
    ArrayList<List_Of_Teachers_Class> list_of_teachers_classes;
    String sem,branch,sub,teacher_id;

    public List_Of_Teachers_Adapter(Context context, ArrayList<List_Of_Teachers_Class> list_of_teachers_classes, String sem, String branch, String sub, String teacher_id) {
        this.context = context;
        this.list_of_teachers_classes = list_of_teachers_classes;
        this.sem = sem;
        this.branch = branch;
        this.sub = sub;
        this.teacher_id= teacher_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_teacher_name,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTeacherName.setText(list_of_teachers_classes.get(position).getTeacher_name());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i= new Intent(context,List_of_Notes.class);
//                i.putExtra("KEY",sem);
//                i.putExtra("BRANCH",branch);
//                i.putExtra("SUBJECT",sub);
//                i.putExtra("TEACHER_ID",teacher_id);
//                context.startActivity(i);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list_of_teachers_classes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTeacherName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeacherName= itemView.findViewById(R.id.txt_teacher_name);

        }
    }
}
