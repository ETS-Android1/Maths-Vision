package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class AdminRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_register);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()==0) {
                    if (((EditText)findViewById(R.id.adminpassword)).getText().toString().matches(((EditText)findViewById(R.id.confirm)).getText().toString()))
                    {
                        DataBaseHelper db = new DataBaseHelper(AdminRegister.this);
                        String name = ((EditText)findViewById(R.id.adminame)).getText().toString();
                        String username=((EditText)findViewById(R.id.adminusername)).getText().toString();
                        String password=((EditText)findViewById(R.id.adminpassword)).getText().toString();
                        String adhar=((EditText)findViewById(R.id.adminaadhar)).getText().toString();
                        String mobile=((EditText)findViewById(R.id.adminmobile)).getText().toString();
                        if (db.setLogin(username,password,name,adhar,mobile)!=-1)
                        {
                            Intent i = new Intent(AdminRegister.this, RegisterSuccess.class);
                            i.putExtra("adminame", name);
                            i.putExtra("id",1);
                            startActivity(i);
                            finish();
                        }else
                            alert(8);


                    }else
                    {
                        alert(7);
                    }


                }
            }
        });
    }
    public int checkEmpty()
    {
        if (((EditText)findViewById(R.id.adminame)).getText().toString().matches(""))
        {

            return alert(1);
        }else if (((EditText)findViewById(R.id.adminusername)).getText().toString().matches(""))
        {

            return alert(2);
        }else if (((EditText)findViewById(R.id.adminpassword)).getText().toString().matches(""))
        {

            return alert(3);
        }else if (((EditText)findViewById(R.id.confirm)).getText().toString().matches(""))
        {

            return alert(4);
        }else if (((EditText)findViewById(R.id.adminaadhar)).getText().toString().matches(""))
        {

            return alert(5);
        }else if (((EditText)findViewById(R.id.adminmobile)).getText().toString().matches(""))
        {

            return alert(6);
        }else return 0;

    }
    public int alert(int n)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(AdminRegister.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Admin Name is empty.");
        else if (n==2)
            alertDialog.setMessage("Username is Empty");
        else if (n==3)
            alertDialog.setMessage("Password is Empty");
        else if (n==4)
            alertDialog.setMessage("Enter Confirm password");
        else if (n==5)
            alertDialog.setMessage("Aadhar Number is Empty");
        else if (n==6)
            alertDialog.setMessage("Mobile Number is Empty");
        else if (n==7)
            alertDialog.setMessage("Confirm password dosen't matches");
        else if (n==8)
            alertDialog.setMessage("Something went wrong...!!!");

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
