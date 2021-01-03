package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class AdminLogin extends AppCompatActivity {

    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        ((CheckBox)findViewById(R.id.checkBox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
                if (value)
                {
                    ((EditText)findViewById(R.id.passwordl)).setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    ((EditText)findViewById(R.id.passwordl)).setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


         db = new DataBaseHelper(AdminLogin.this);
        Cursor c= db.getLogin();
        c.moveToFirst();
        final String username=c.getString(c.getColumnIndex(DataBaseHelper.username));
        final String password=c.getString(c.getColumnIndex(DataBaseHelper.password));
        final String name=c.getString(c.getColumnIndex(DataBaseHelper.name));

        ((EditText)findViewById(R.id.usernamel)).setText(username);
        ((TextView)findViewById(R.id.welnam)).setText(name);

        findViewById(R.id.loginbt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      if (checkEmpty()==0)
                      {
                          if (!((EditText)findViewById(R.id.usernamel)).getText().toString().matches(username))
                              alert(3);
                          else if (!((EditText)findViewById(R.id.passwordl)).getText().toString().matches(password))
                              alert(4);
                          else {
                              Intent i = new Intent(AdminLogin.this,LoginSuccess.class);
                              db = new DataBaseHelper(AdminLogin.this);
                              db.setStatus("in");
                              startActivity(i);
                              finish();
                          }
                      }
            }
        });


    }
    public int checkEmpty()
    {
        if (((EditText)findViewById(R.id.usernamel)).getText().toString().matches(""))
            return alert(1);
        else if (((EditText)findViewById(R.id.passwordl)).getText().toString().matches(""))
        return alert(2);
        else return 0;
    }

    public int alert(int n)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(AdminLogin.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Username is empty.");
        else if (n==2)
            alertDialog.setMessage("Password is Empty");
        else if (n==3)
            alertDialog.setMessage("Invalid Username");
        else if (n==4)
            alertDialog.setMessage("Invalid Password");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        return 1;
    }
}
