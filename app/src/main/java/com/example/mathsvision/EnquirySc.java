package com.example.mathsvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class EnquirySc extends AppCompatActivity {
    int activityId;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_sc);
        getSupportActionBar().hide();
        activityId=getIntent().getExtras().getInt("aid");
       if (activityId==2)
       {
           ((GifImageView)findViewById(R.id.gif)).setImageResource(R.drawable.studentgif);
           ((TextView)findViewById(R.id.title)).setText("*  *  *  *  *   Student Info.  *  *  *  *  *");
           ((TextView)findViewById(R.id.addenq2)).setText("Add Student Info.");
           ((TextView)findViewById(R.id.searchenq2)).setText("Search Student Info.");
           ((TextView)findViewById(R.id.editenq2)).setText("Edit Student Info.");
           ((TextView)findViewById(R.id.deletenq2)).setText("Delete Student Info.");

       }
        findViewById(R.id.addenq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activityId==1)
                 i = new Intent(EnquirySc.this,AddEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,AddStudentInfo.class);
                i.putExtra("Id",0);
                startActivity(i);
            }
        });


        findViewById(R.id.addenq2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activityId==1)
                    i = new Intent(EnquirySc.this,AddEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,AddStudentInfo.class);
                i.putExtra("Id",0);
                startActivity(i);
            }
        });


        findViewById(R.id.searchenq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                 i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",1);
                startActivity(i);
            }
        });


        findViewById(R.id.searchenq2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                    i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",1);
                startActivity(i);
            }
        });


        findViewById(R.id.editenq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                    i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",2);
                startActivity(i);
            }
        });


        findViewById(R.id.editenq2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                    i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",2);
                startActivity(i);
            }
        });


        findViewById(R.id.deletenq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                    i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",3);
                startActivity(i);
            }
        });


        findViewById(R.id.deletenq2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                    i = new Intent(EnquirySc.this,SearchEnquiry.class);
                else
                    i = new Intent(EnquirySc.this,SearchStudentInfo.class);
                i.putExtra("Id",3);
                startActivity(i);
            }
        });



    }
}
