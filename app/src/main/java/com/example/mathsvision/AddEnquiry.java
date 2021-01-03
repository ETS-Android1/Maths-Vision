package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEnquiry extends AppCompatActivity  {


    String[] classes = { "Class to Join", "12th", "11th", "10th", "9th", "8th","7th"};
    String[] board = { "Select Board", "MP Board", "CBSE Board"};
    public String eid,eqdate,eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks;
    ArrayAdapter a ,aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_enquiry);
        getSupportActionBar().hide();

        eid=new GetId().getEnquiryId("E");
        ((TextView)findViewById(R.id.eid)).setText(" Enquiry Id : "+eid);

        eqdate=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        ((TextView) findViewById(R.id.session)).setText("Session : "+new GetSession().getSession());

         a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,classes);

        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner)findViewById(R.id.eqclasstojoin)).setAdapter(a);

        ((Spinner)findViewById(R.id.eqclasstojoin)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eqclasstojoin=classes[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                alert(3);
            }
        });

         aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,board);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.eqboard)).setAdapter(aa);
        ((Spinner)findViewById(R.id.eqboard)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eqboard=board[position];

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                alert(5);
            }
        });
        findViewById(R.id.eqsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEmpty()==0)
                {
                    eqstname=((EditText)findViewById(R.id.eqstname)).getText().toString();
                    eqstfname=((EditText)findViewById(R.id.eqstfname)).getText().toString();
                    eqschoolname=((EditText)findViewById(R.id.eqschoolname)).getText().toString();
                    eqmobno=((EditText)findViewById(R.id.eqmobno)).getText().toString();
                    eqwhatsappno=((EditText)findViewById(R.id.eqwhatsappno)).getText().toString();
                    eqaddress=((EditText)findViewById(R.id.eqaddress)).getText().toString();
                    eqreferences=((EditText)findViewById(R.id.eqreferences)).getText().toString();
                    eqremarks=((EditText)findViewById(R.id.eqremarks)).getText().toString();
                    DataBaseHelper db =new DataBaseHelper(AddEnquiry.this);
                    if(eqwhatsappno.matches(""))
                        eqwhatsappno="Null";
                     if(eqreferences.matches(""))
                        eqreferences="Null";
                     if(eqremarks.matches(""))
                        eqremarks="Null";

                    Enquiry e =new Enquiry(eid,eqdate,eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks);
                    if (isConnected()) {
                        if (new FirebaseHelper().addEnquiry(e) && db.setEnquiry(eid, eqstname, eqstfname, eqclasstojoin, eqschoolname, eqboard, eqmobno, eqwhatsappno, eqaddress, eqreferences, eqremarks, eqdate,0) != -1) {
                            alert(9);
                            ((EditText) findViewById(R.id.eqstname)).setText("");
                            ((EditText) findViewById(R.id.eqstfname)).setText("");
                            ((EditText) findViewById(R.id.eqschoolname)).setText("");
                            ((EditText) findViewById(R.id.eqmobno)).setText("");
                            ((EditText) findViewById(R.id.eqwhatsappno)).setText("");
                            ((EditText) findViewById(R.id.eqaddress)).setText("");
                            ((EditText) findViewById(R.id.eqreferences)).setText("");
                            ((EditText) findViewById(R.id.eqremarks)).setText("");
                            ((Spinner)findViewById(R.id.eqclasstojoin)).setAdapter(a);
                            ((Spinner)findViewById(R.id.eqboard)).setAdapter(aa);
                            eid=new GetId().getEnquiryId("E");
                            ((TextView)findViewById(R.id.eid)).setText(" Enquiry Id : "+eid);
                        } else
                            alert(8);
                    }else
                        alert(10);


                }
            }
        });

    }
    public int checkEmpty() {
        if (((EditText)findViewById(R.id.eqstname)).getText().toString().matches(""))
        {
            return alert(1);
        }else if (((EditText)findViewById(R.id.eqstfname)).getText().toString().matches(""))
        {
            return alert(2);
        }
        else if (eqclasstojoin.matches("Class to Join"))
        {
            return alert(3);
        }
        else if (((EditText)findViewById(R.id.eqschoolname)).getText().toString().matches(""))
        {
            return alert(4);
        }else if (eqboard.matches("Select Board"))
        {
            return alert(5);
        }
        else if (((EditText)findViewById(R.id.eqmobno)).getText().toString().matches(""))
        {
            return alert(6);
        }
        else if (((EditText)findViewById(R.id.eqaddress)).getText().toString().matches(""))
        {
            return alert(7);
        }

        else return 0;

    }
    public int alert(int n) {
        AlertDialog alertDialog = new AlertDialog.Builder(AddEnquiry.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Enter Student Name.");
        else if (n==2)
            alertDialog.setMessage("Enter Father's Name.");
        else if (n==3)
            alertDialog.setMessage("Select Class to Join");
        else if (n==4)
            alertDialog.setMessage("Enter School name");
        else if (n==5)
            alertDialog.setMessage("Select Board");
        else if (n==6)
            alertDialog.setMessage("Enter Mobile number");
        else if (n==7)
            alertDialog.setMessage("Enter Address");
        else if (n==8)
            alertDialog.setMessage("Something Went Wrong...!!!");
        else if (n==9)
            alertDialog.setMessage("Information Added Successfully...");
        else if (n==10)
            alertDialog.setMessage("Please turn on your internet connection.");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        return 1;
    }

    public boolean isConnected()
    {

        ConnectivityManager c=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo n = c.getActiveNetworkInfo();
        return n!=null && n.isConnected();

    }

}
