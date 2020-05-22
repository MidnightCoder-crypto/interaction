package com.example.interacting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Uploading_Interface extends AppCompatActivity {
    DatabaseReference dbref, teacher_ref, compare_ref;
    Button btn_select, btn_upload;
    EditText txt;
    String sem = "";
    String dept = "";
    String sub = "";
    StorageReference storageReference;
    public static final String STORAGE_PATH_UPLOADS = "uploads/";
    String uid = "";
    ProgressBar progressBar;
    Uri fileUri;
    String current_date;
    TextView txtProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading__interface);
        txt = findViewById(R.id.file_name);
        btn_select = findViewById(R.id.btnSelect);
        btn_upload = findViewById(R.id.btnUpload);
        sem = getIntent().getStringExtra("KEY");
        dept = getIntent().getStringExtra("DEPT");
        txtProgress= findViewById(R.id.textProgress);
        sub = getIntent().getStringExtra("SUBJECT_NAME");
        progressBar= findViewById(R.id.progressbar);
        txtProgress.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        dbref = FirebaseDatabase.getInstance().getReference().child("Material").child(sem).child(dept).child("subjects").child(sub);
        teacher_ref = dbref.child("teachers");
        storageReference = FirebaseStorage.getInstance().getReference();
        current_date= new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());

        teacher_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    uid = dataSnapshot1.getKey();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        dbref = FirebaseDatabase.getInstance().getReference().child("Uploads");
        storageReference = FirebaseStorage.getInstance().getReference();


        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("application/pdf");
                startActivityForResult(Intent.createChooser(i,"Select PDF file"),438);
            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileUri!=null)
                {
                    Toast.makeText(Uploading_Interface.this, ""+fileUri, Toast.LENGTH_SHORT).show();


                }
                else
                    Toast.makeText(Uploading_Interface.this, "Select File", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==438 && resultCode== RESULT_OK && data!=null && data.getData()!=null)
        {
            fileUri= data.getData();
            uploadPDFfiles(fileUri);
        }







    }

    private void uploadPDFfiles(Uri fileUri) {
        progressBar.setVisibility(View.VISIBLE);
        txtProgress.setVisibility(View.VISIBLE);



        StorageReference reference= storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
        reference.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uri= taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url= uri.getResult();

//                        Notes notes= new Notes(txt.getText().toString(),url.toString(),currentDate);
                        compare_ref= dbref.child(uid);
//                        compare_ref.child(compare_ref.push().getKey()).setValue(notes);
                        Toast.makeText(Uploading_Interface.this, "Successfull Upload", Toast.LENGTH_SHORT).show();
                        progressBar.setProgress(0);
                        txtProgress.setText("Uploaded 100%");




                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        progressBar.setProgress((int) progress);
                        txtProgress.setText(progress+" %");

                    }
                });








    }
}