package com.example.mathsvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class RegisterSuccess extends AppCompatActivity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        getSupportActionBar().hide();
        id=getIntent().getExtras().getInt("id");
        if (id==1) {
            String s[]=getIntent().getExtras().get("adminame").toString().split(" ");
            ((TextView) findViewById(R.id.welnote2)).setText("Welcome " + s[0] + ",\n\n" + " Now you are the admin of Maths Vision.");
        }else {
            ((TextView) findViewById(R.id.welnote)).setText("Student Registered Successfully");
            ((TextView) findViewById(R.id.welnote2)).setVisibility(View.INVISIBLE);
        }
            new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (id==1)
                {
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();
                    Intent i = new Intent(RegisterSuccess.this,AdminLogin.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(RegisterSuccess.this,AddStudentInfo.class);
                    i.putExtra("Id",0);
                    startActivity(i);
                }
                finish();
            }
        },3000);
    }
}
