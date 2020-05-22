package com.example.interacting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Notes> notes;

    public NotesAdapter(Context context, ArrayList<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtfile_name.setText(notes.get(position).getFile_name());
        holder.txtfileupload_date.setText(String.valueOf(notes.get(position).getFile_upload_date()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtfile_name,txtfileupload_date;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfile_name= itemView.findViewById(R.id.txt_notes);
            txtfileupload_date= itemView.findViewById(R.id.txt_notes_date);

        }

    }


}
