package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.MotionEvent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowEnquiryData extends AppCompatActivity {

    KeyListener keyListener ;
    int activityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enquiry_data);
        getSupportActionBar().hide();
        ((TextView) findViewById(R.id.session3)).setText("Session : "+new GetSession().getSession());
        activityId=getIntent().getExtras().getInt("Id");
        keyListener=((EditText)findViewById(R.id.stname)).getKeyListener();
        ((EditText)findViewById(R.id.stname)).setKeyListener(null);
        ((EditText)findViewById(R.id.stfname)).setKeyListener(null);
        ((EditText)findViewById(R.id.classes)).setKeyListener(null);
        ((EditText)findViewById(R.id.date)).setKeyListener(null);
        ((EditText)findViewById(R.id.schoolname)).setKeyListener(null);
        ((EditText)findViewById(R.id.board)).setKeyListener(null);
        ((EditText)findViewById(R.id.mobno)).setKeyListener(null);
        ((EditText)findViewById(R.id.whatsappno)).setKeyListener(null);
        ((EditText)findViewById(R.id.address)).setKeyListener(null);
        ((EditText)findViewById(R.id.references)).setKeyListener(null);
        ((EditText)findViewById(R.id.remarks)).setKeyListener(null);



        ((EditText)findViewById(R.id.whatsappno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.whatsappno)).getRight() - ((EditText)findViewById(R.id.whatsappno)).getCompoundDrawables()[2].getBounds().width())){
                      if (!((EditText)findViewById(R.id.whatsappno)).getText().toString().matches("Null")) {
                          Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.whatsappno)).getText().toString());
                          Intent i = new Intent(Intent.ACTION_DIAL, u);
                          startActivity(i);
                          return true;
                      }else alert(0);
                    }
                }
                return false;
            }
        });

        ((EditText)findViewById(R.id.mobno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.mobno)).getRight() - ((EditText)findViewById(R.id.mobno)).getCompoundDrawables()[2].getBounds().width())){

                            Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.mobno)).getText().toString());
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                            return true;

                    }
                }
                return false;
            }
        });


         ((TextView)findViewById(R.id.eid)).setText(" Enquiry Id : "+getIntent().getExtras().get("eid").toString());
        ((EditText)findViewById(R.id.stname)).setText(getIntent().getExtras().get("name").toString());
        ((EditText)findViewById(R.id.stfname)).setText(getIntent().getExtras().get("fname").toString());
        ((EditText)findViewById(R.id.date)).setText(getIntent().getExtras().get("date").toString());
        ((EditText)findViewById(R.id.classes)).setText(getIntent().getExtras().get("classes").toString());
        ((EditText)findViewById(R.id.schoolname)).setText(getIntent().getExtras().get("schl").toString());
        ((EditText)findViewById(R.id.board)).setText(getIntent().getExtras().get("board").toString());
        ((EditText)findViewById(R.id.whatsappno)).setText(getIntent().getExtras().get("wtspno").toString());
        ((EditText)findViewById(R.id.address)).setText(getIntent().getExtras().get("address").toString());
        ((EditText)findViewById(R.id.references)).setText(getIntent().getExtras().get("ref").toString());
        ((EditText)findViewById(R.id.remarks)).setText(getIntent().getExtras().get("remark").toString());
        ((EditText)findViewById(R.id.mobno)).setText(getIntent().getExtras().get("mobno").toString());

        if (activityId==2)
        {
            ((TextView)findViewById(R.id.textView16)).setText("*  *  *  *  *  Edit Details  *  *  *  *  *");
            ((Button)findViewById(R.id.adtost)).setText("Edit Enquiry");
            ((EditText)findViewById(R.id.stname)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.stfname)).setKeyListener(keyListener);
            //((EditText)findViewById(R.id.classes)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.schoolname)).setKeyListener(keyListener);
            //((EditText)findViewById(R.id.board)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.mobno)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.mobno)).setInputType(InputType.TYPE_CLASS_NUMBER);
            ((EditText)findViewById(R.id.whatsappno)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.whatsappno)).setInputType(InputType.TYPE_CLASS_NUMBER);
            ((EditText)findViewById(R.id.address)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.references)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.remarks)).setKeyListener(keyListener);

        }

        findViewById(R.id.board).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==2)
                {
                    String[] colors = {"MP Board", "CBSE Board"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowEnquiryData.this);
                    builder.setTitle("Select Class");
                    builder.setItems(colors, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.board)).setText("MP Board");

                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.board)).setText("CBSE Board");
                            }


                        }
                    });
                    builder.show();
                }
            }
        });
        findViewById(R.id.classes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activityId==2)
                {

                    String[] colors = {"12th", "11th", "10th", "9th", "8th","7th"};

                    AlertDialog.Builder builder = new AlertDialog.Builder(ShowEnquiryData.this);
                    builder.setTitle("Select Class");
                    builder.setItems(colors, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("12th");

                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("11th");
                            }else if (which==2)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("10th");
                            }
                            else if (which==3)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("9th");
                            }
                            else if (which==4)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("8th");
                            }
                            else if (which==5)
                            {
                                ((EditText)findViewById(R.id.classes)).setText("7th");
                            }


                        }
                    });
                    builder.show();
                }
            }
        });


        findViewById(R.id.adtost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityId==1)
                {
                    Enquiry e = new Enquiry(getIntent().getExtras().get("eid").toString(),getIntent().getExtras().get("date").toString(),getIntent().getExtras().get("name").toString(),getIntent().getExtras().get("fname").toString(),getIntent().getExtras().get("classes").toString(),getIntent().getExtras().get("schl").toString(),getIntent().getExtras().get("board").toString(),getIntent().getExtras().get("mobno").toString(),getIntent().getExtras().get("wtspno").toString(),getIntent().getExtras().get("address").toString(),getIntent().getExtras().get("ref").toString(),getIntent().getExtras().get("remark").toString());
                    Intent i = new Intent(ShowEnquiryData.this,AddStudentInfo.class);
                    i.putExtra("enq",e);
                    i.putExtra("Id",1);
                    startActivity(i);
                }
                else {
                    if (checkEmpty()==0)
                    {
                        String eqdate, eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks;
                        eqstname=((EditText)findViewById(R.id.stname)).getText().toString();
                        eqstfname=((EditText)findViewById(R.id.stfname)).getText().toString();
                        eqclasstojoin=((EditText)findViewById(R.id.classes)).getText().toString();
                        eqschoolname=((EditText)findViewById(R.id.schoolname)).getText().toString();
                        eqboard=((EditText)findViewById(R.id.board)).getText().toString();
                        eqmobno=((EditText)findViewById(R.id.mobno)).getText().toString();
                        eqdate=((EditText)findViewById(R.id.date)).getText().toString();
                        eqwhatsappno=((EditText)findViewById(R.id.whatsappno)).getText().toString();
                        eqaddress=((EditText)findViewById(R.id.address)).getText().toString();
                        eqreferences=((EditText)findViewById(R.id.references)).getText().toString();
                        eqremarks=((EditText)findViewById(R.id.remarks)).getText().toString();
                        DataBaseHelper db =new DataBaseHelper(ShowEnquiryData.this);
                        if(eqwhatsappno.matches(""))
                            eqwhatsappno="Null";
                        if(eqreferences.matches(""))
                            eqreferences="Null";
                        if(eqremarks.matches(""))
                            eqremarks="Null";
                        if (isConnected())
                        {
                            Enquiry e = new Enquiry(getIntent().getExtras().get("eid").toString(),eqdate,eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks);
                            if(new FirebaseHelper().addEnquiry(e)&&db.setEnquiry(getIntent().getExtras().get("eid").toString(),eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks,eqdate,2)!=0)
                            {
                                alert(8);
                            }
                        }else alert(9);

                    }

                }

            }
        });



    }
    public int alert(int n)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(ShowEnquiryData.this).create();
        alertDialog.setTitle("Alert");
        if (n==0)
            alertDialog.setMessage("Whatsapp Number is Null");
        else if (n==1)
            alertDialog.setMessage("Student Name is Empty");
        else if (n==2)
            alertDialog.setMessage("Father's Name is Empty");
        else if (n==3)
            alertDialog.setMessage("Class is Empty");
        else if (n==4)
            alertDialog.setMessage("School Name is Empty");
        else if (n==5)
            alertDialog.setMessage("Board is Empty");
        else if (n==6)
            alertDialog.setMessage("Mobile Number is Empty");
        else if (n==7)
            alertDialog.setMessage("Address is Empty");
        else if (n==8)
            alertDialog.setMessage("Information Updated Successfully..");
        else if (n==9)
            alertDialog.setMessage("Please turn on your Internet Connection...");

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        return 1;
    }
    public int checkEmpty()
    {
        if (((EditText)findViewById(R.id.stname)).getText().toString().matches(""))
            return alert(1);
        else if (((EditText)findViewById(R.id.stfname)).getText().toString().matches(""))
            return alert(2);
        else if (((EditText)findViewById(R.id.classes)).getText().toString().matches(""))
            return alert(3);
        else if (((EditText)findViewById(R.id.schoolname)).getText().toString().matches(""))
            return alert(4);
        else if (((EditText)findViewById(R.id.board)).getText().toString().matches(""))
            return alert(5);
        else if (((EditText)findViewById(R.id.mobno)).getText().toString().matches(""))
            return alert(6);
        else if (((EditText)findViewById(R.id.address)).getText().toString().matches(""))
            return alert(7);
        else return 0;
    }
    public boolean isConnected()
    {

        ConnectivityManager c=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo n = c.getActiveNetworkInfo();
        return n!=null && n.isConnected();

    }

}
