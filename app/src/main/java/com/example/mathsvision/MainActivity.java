package com.example.mathsvision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=4500;
    Animation topAnim;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        image=findViewById(R.id.imageView);
        image.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    startActivity(new Intent(MainActivity.this, AdminRegister.class));
                }else {
                       DataBaseHelper db = new DataBaseHelper(MainActivity.this);
                       Cursor c = db.getLogin();
                       c.moveToFirst();
                       String st = c.getString(c.getColumnIndex(DataBaseHelper.status));
                       if (st.matches("out"))
                    startActivity(new Intent(MainActivity.this, AdminLogin.class));
                       else
                           startActivity(new Intent(MainActivity.this, Home.class));
                }
                    finish();
                
            }
        },SPLASH_SCREEN);
    }
}
