package com.example.mathsvision;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddStudentInfo extends AppCompatActivity {

    String[] classes = { "Class to Join", "12th", "11th", "10th", "9th", "8th","7th"};
    String[] board = { "Select Board", "MP Board", "CBSE Board"};
    String[] course = { "Select Course", "PCM", "PCB"};
    static final int SELECT_PHOTO=1;
    int cls,brd,crs;
    int activityId;
    StudentInformation s;
    CircleImageView stimg ;
    Bitmap selectedImage;
    DataBaseHelper db;
    EditText stname,stfname,stmname,stdob,stschname,stfquali,stfoccu,stmquali,stmoccu,stmob,stwtsp,stfmob,stfwtsp,stmmob,stmwtsp,stomob,stladdress,stpaddress,stambition,stremark;
    String sid;
    ArrayAdapter boardad,classad,coursead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_info);
        getSupportActionBar().hide();
        s=new StudentInformation();
        stimg=(CircleImageView) findViewById(R.id.stimage);
        db=new DataBaseHelper(AddStudentInfo.this);
        sid=new GetId().getEnquiryId("S");
        ((TextView)findViewById(R.id.sid)).setText("Student Id : "+sid);
        activityId=getIntent().getExtras().getInt("Id");

        stname=(EditText)findViewById(R.id.stname);
        stfname=(EditText)findViewById(R.id.stfname);
        stmname=(EditText)findViewById(R.id.stmname);
        stdob=(EditText)findViewById(R.id.stdob);
        stschname=(EditText)findViewById(R.id.stschoolname);
        stfquali=(EditText)findViewById(R.id.stfquali);
        stfoccu=(EditText)findViewById(R.id.stfoccu);
        stmquali=(EditText)findViewById(R.id.stmquali);
        stmoccu=(EditText)findViewById(R.id.stmoccu);
        stmob=(EditText)findViewById(R.id.stmobno);
        stwtsp=(EditText)findViewById(R.id.stwtspno);
        stfmob=(EditText)findViewById(R.id.stfmobno);
        stfwtsp=(EditText)findViewById(R.id.stfwtspno);
        stmmob=(EditText)findViewById(R.id.stmmobno);
        stmwtsp=(EditText)findViewById(R.id.stmwtspno);
        stomob=(EditText)findViewById(R.id.stothermno123);
        stladdress=(EditText)findViewById(R.id.stladdress);
        stpaddress=(EditText)findViewById(R.id.stpaddress);
        stambition=(EditText)findViewById(R.id.stambition);
        stremark=(EditText)findViewById(R.id.stremark);


        if (activityId==1)
        {
            Enquiry e= (Enquiry)getIntent().getSerializableExtra("enq");
            stname.setText(e.getEqstname());
            stfname.setText(e.getEqstfname());
            stschname.setText(e.getEqschoolname());
            stmob.setText(e.getEqmobno());
            stwtsp.setText(e.getEqwhatsappno());
            stladdress.setText(e.getEqaddress());
            stremark.setText(e.getEqremarks());
        }



        //******************************** SPINNER PART *****************************************

        classad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,classes);
        classad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.stclass)).setAdapter(classad);
        ((Spinner)findViewById(R.id.stclass)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cls=position;
                if (position==1)
                    ((Spinner)findViewById(R.id.stcourse)).setVisibility(View.VISIBLE);
               else if (position==2)
                    ((Spinner)findViewById(R.id.stcourse)).setVisibility(View.VISIBLE);
               else ((Spinner)findViewById(R.id.stcourse)).setVisibility(View.INVISIBLE);
               s.setStudentClass(classes[position]);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        boardad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,board);
        boardad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.stboard)).setAdapter(boardad);
        ((Spinner)findViewById(R.id.stboard)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               brd=position;
                s.setStudentBoard(board[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        coursead = new ArrayAdapter(this,android.R.layout.simple_spinner_item,course);
        coursead.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.stcourse)).setAdapter(coursead);
        ((Spinner)findViewById(R.id.stcourse)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               crs=position;

                s.setStudentCourse(course[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //******************************** SPINNER PART *****************************************



        //******************************** IMAGE SELECTION PART *****************************************


        stimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] colors = {"Pick from Gallery", "Remove image"};

                AlertDialog.Builder builder = new AlertDialog.Builder(AddStudentInfo.this);
                builder.setTitle("Set Your Image");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0)
                        {
                            Intent photoPicker = new Intent(Intent.ACTION_PICK);
                            photoPicker.setType("image/*");
                            startActivityForResult(photoPicker,SELECT_PHOTO);

                        }else if (which==1)
                        {
                            stimg.setImageResource(R.drawable.userimg);

                        }
                    }
                });
                builder.show();
            }
        });


        //******************************** IMAGE SELECTION PART *****************************************


        findViewById(R.id.stsave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty()==0)
                {
                    s.setSid(sid);
                    s.setStudentName(stname.getText().toString());
                    s.setStudentFatherName(stfname.getText().toString());
                    s.setStudentMotherName(stmname.getText().toString());
                    s.setStudentDOB(stdob.getText().toString());
                    if (cls!=1&&cls!=2)
                        s.setStudentCourse("Null");
                    s.setStudentSchoolname(stschname.getText().toString());
                    if (stfquali.getText().toString().matches(""))
                        s.setStudentFatherQualification("Null");
                    else
                        s.setStudentFatherQualification(stfquali.getText().toString());


                    if (stmquali.getText().toString().matches(""))
                        s.setStudentMotherQualification("Null");
                    else
                        s.setStudentMotherQualification(stmquali.getText().toString());


                    if (stfoccu.getText().toString().matches(""))
                        s.setStudentFatherOccupation("Null");
                    else
                        s.setStudentFatherOccupation(stfoccu.getText().toString());

                    if (stmoccu.getText().toString().matches(""))
                        s.setStudentMotherOccupation("Null");
                    else
                        s.setStudentMotherOccupation(stmoccu.getText().toString());

                    s.setStudentMobNo(stmob.getText().toString());
                    s.setStudentfMobNo(stfmob.getText().toString());
                    s.setStudentmMobNo(stmmob.getText().toString());

                    if (stwtsp.getText().toString().matches(""))
                        s.setStudentWtspNo("Null");
                    else
                        s.setStudentWtspNo(stwtsp.getText().toString());


                    if (stfwtsp.getText().toString().matches(""))
                        s.setStudentfWtspNo("Null");
                    else
                        s.setStudentfWtspNo(stfwtsp.getText().toString());


                    if (stmwtsp.getText().toString().matches(""))
                        s.setStudentmWtspNo("Null");
                    else
                        s.setStudentmWtspNo(stmwtsp.getText().toString());

                    if (((EditText)findViewById(R.id.stothermno123)).getText().toString().matches(""))
                        s.setStudentOtherMobNo("Null");
                    else
                        s.setStudentOtherMobNo(((EditText)findViewById(R.id.stothermno123)).getText().toString());

                    if (stladdress.getText().toString().matches(""))
                        s.setStudentLoacalAddress("Null");
                    else
                        s.setStudentLoacalAddress(stladdress.getText().toString());

                    if (stpaddress.getText().toString().matches(""))
                        s.setStudentPermanentAddress("Null");
                    else
                        s.setStudentPermanentAddress(stpaddress.getText().toString());

                    if (stambition.getText().toString().matches(""))
                        s.setStudentAmbitions("Null");
                    else
                        s.setStudentAmbitions(stambition.getText().toString());

                    if (stremark.getText().toString().matches(""))
                        s.setStudentRemark("Null");
                    else
                        s.setStudentRemark(stremark.getText().toString());
                    s.setStudentAdmissionDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));

                    //************************  SET IMAGE ***********************
                    stimg.setDrawingCacheEnabled(true);
                    stimg.buildDrawingCache();
                    Bitmap bitmap = stimg.getDrawingCache();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    byte[] data = baos.toByteArray();
                    s.setStimage(data);

                    if (isConnected())
                    {

                        if (db.setStudentInfo(s,0)!=-1&&new FirebaseHelper().addStudent(s))
                        {
                            Intent i = new Intent(AddStudentInfo.this,RegisterSuccess.class);
                            i.putExtra("id",2);
                            startActivity(i);
                            finish();

                        }else
                            alert(6);
                    }
                    else alert(5);
                }

            }
        });


    }//end of onCreate()



    //******************************** IMAGE SELECTION METHODS *****************************************

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==0)
        {
            if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                stimg.setEnabled(true);
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SELECT_PHOTO)
        {
            if (resultCode==RESULT_OK)
            {
                try {
                    final Uri imageUri=data.getData();
                    final InputStream imageStream=getContentResolver().openInputStream(imageUri);
                    selectedImage= BitmapFactory.decodeStream(imageStream);
                    stimg.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //******************************** IMAGE SELECTION METHODS *****************************************

    public int checkEmpty()
    {
        if (stname.getText().toString().matches(""))
        {
            stname.setError("Enter Student Name");
            return 1;
        }
        else if (stfname.getText().toString().matches(""))
        {
            stfname.setError("Enter Father Name");
            return 1;
        }
        else if (stmname.getText().toString().matches(""))
        {
            stmname.setError("Enter Mother Name");
            return 1;
        }
        else if (stdob.getText().toString().matches(""))
        {
            stdob.setError("Enter Student DOB");
            return 1;
        }
        else if (stschname.getText().toString().matches(""))
        {
            stschname.setError("Enter School Name");
            return 1;
        }
        else if (stmob.getText().toString().matches(""))
        {
            stmob.setError("Enter Student's Mobile No.");
            return 1;
        }
        else if (stfmob.getText().toString().matches(""))
        {
            stfmob.setError("Enter Father's Mobile No.");
            return 1;
        }
        else if(cls==0)
            return alert(1);
        else if(brd==0)
            return alert(2);
        else if((cls==1||cls==2)&&crs==0)
            return alert(3);
        else if (stladdress.getText().toString().matches("")&&stpaddress.getText().toString().matches(""))
            return alert(4);
        else
            return 0;
    }


    public int alert(int n) {
        AlertDialog alertDialog = new AlertDialog.Builder(AddStudentInfo.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Select Class...!!!");
        else if (n==2)
            alertDialog.setMessage("Select Board...!!!");
        else if (n==3)
            alertDialog.setMessage("Select any Course...!!!");
        else if (n==4)
            alertDialog.setMessage("Enter Student's Address..!!");
        else if (n==5)
            alertDialog.setMessage("Turn on your Internet Connection");
        else if (n==6)
            alertDialog.setMessage("Something went Wrong");

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
