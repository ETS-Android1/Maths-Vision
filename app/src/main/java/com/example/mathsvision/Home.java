package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        findViewById(R.id.enquiry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,EnquirySc.class);
                i.putExtra("aid",1);
                startActivity(i);
            }
        });

        findViewById(R.id.studentinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,EnquirySc.class);
                i.putExtra("aid",2);
                startActivity(i);
            }
        });



        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);

                builder.setIcon(R.mipmap.ic_launcher_warning);
                builder.setCancelable(false);
                builder.setTitle("Exit");
                builder.setMessage("Are you sure, You want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DataBaseHelper db = new  DataBaseHelper(Home.this);
                        db.setStatus("out");
                        startActivity(new Intent(getApplicationContext(),AdminLogin.class));
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Home.this, "You clicked over No", Toast.LENGTH_SHORT).show();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        findViewById(R.id.buttonbackup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,BackupRestore.class);
                startActivity(i);
            }
        });
        findViewById(R.id.buttonsetting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this,StudentInfo.class);
                startActivity(i);
            }
        });


    }

}
