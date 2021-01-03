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
import android.os.Environment;
import android.os.Handler;
import android.text.method.KeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowStudentDetails extends AppCompatActivity {

    StudentInformation s;
    int activityId;
    static final int SELECT_PHOTO=1;
    CircleImageView stimg;
    Bitmap selectedImage;
    boolean flag=false,flag2=true;
    EditText stname,stfname,stmname,stdob,stschname,stfquali,stfoccu,stmquali,stmoccu,stmob,stwtsp,stfmob,stfwtsp,stmmob,stmwtsp,stomob,stladdress,stpaddress,stambition,stremark;
    KeyListener keyListener,keyListenern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_details);
        getSupportActionBar().hide();
        activityId=getIntent().getExtras().getInt("Id");
         s = (StudentInformation) getIntent().getSerializableExtra("std");
         keyListener=((EditText)findViewById(R.id.shstname)).getKeyListener();
         keyListenern=((EditText)findViewById(R.id.shstmobno)).getKeyListener();
         stimg=(CircleImageView)findViewById(R.id.shstimage);


        ((EditText)findViewById(R.id.shstmobno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstmobno)).getRight() - ((EditText)findViewById(R.id.shstmobno)).getCompoundDrawables()[2].getBounds().width())){
                        Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstmobno)).getText().toString());
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);
                        return true;
                    }
                }
                return false;
            }
        });


        ((EditText)findViewById(R.id.shstfmobno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstfmobno)).getRight() - ((EditText)findViewById(R.id.shstfmobno)).getCompoundDrawables()[2].getBounds().width())){
                        Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstfmobno)).getText().toString());
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);
                        return true;
                    }
                }
                return false;
            }
        });

        ((EditText)findViewById(R.id.shstmmobno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstmmobno)).getRight() - ((EditText)findViewById(R.id.shstmmobno)).getCompoundDrawables()[2].getBounds().width())){
                        Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstmmobno)).getText().toString());
                        Intent i = new Intent(Intent.ACTION_DIAL, u);
                        startActivity(i);
                        return true;
                    }
                }
                return false;
            }
        });


        ((EditText)findViewById(R.id.shstwtspno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstwtspno)).getRight() - ((EditText)findViewById(R.id.shstwtspno)).getCompoundDrawables()[2].getBounds().width())){
                        if (!((EditText)findViewById(R.id.shstwtspno)).getText().toString().matches("Null")) {
                            Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstwtspno)).getText().toString());
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                            return true;
                        }else{ ((EditText)findViewById(R.id.shstwtspno)).setError("Number is Null");
                        removeError();
                        }
                    }
                }
                return false;
            }
        });



        ((EditText)findViewById(R.id.shstfwtspno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstfwtspno)).getRight() - ((EditText)findViewById(R.id.shstfwtspno)).getCompoundDrawables()[2].getBounds().width())){
                        if (!((EditText)findViewById(R.id.shstfwtspno)).getText().toString().matches("Null")) {
                            Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstfwtspno)).getText().toString());
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                            return true;
                        }else{ ((EditText)findViewById(R.id.shstfwtspno)).setError("Number is Null");
                        removeError();
                        }
                    }
                }
                return false;
            }
        });



        ((EditText)findViewById(R.id.shstmwtspno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstmwtspno)).getRight() - ((EditText)findViewById(R.id.shstmwtspno)).getCompoundDrawables()[2].getBounds().width())){
                        if (!((EditText)findViewById(R.id.shstmwtspno)).getText().toString().matches("Null")) {
                            Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstmwtspno)).getText().toString());
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                            return true;
                        }else
                            { ((EditText)findViewById(R.id.shstmwtspno)).setError("Number is Null");
                        removeError();
                        }
                    }
                }
                return false;
            }
        });



        ((EditText)findViewById(R.id.shstomobno)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (event.getRawX()>=(((EditText)findViewById(R.id.shstomobno)).getRight() - ((EditText)findViewById(R.id.shstomobno)).getCompoundDrawables()[2].getBounds().width())){
                        if (!((EditText)findViewById(R.id.shstomobno)).getText().toString().matches("Null")) {
                            Uri u = Uri.parse("tel:" + ((EditText) findViewById(R.id.shstomobno)).getText().toString());
                            Intent i = new Intent(Intent.ACTION_DIAL, u);
                            startActivity(i);
                            return true;
                        }else{ ((EditText)findViewById(R.id.shstomobno)).setError("Number is Null");
                        removeError();
                        }
                    }
                }
                return false;
            }
        });


        stname=(EditText)findViewById(R.id.shstname);
        stfname=(EditText)findViewById(R.id.shstfname);
        stmname=(EditText)findViewById(R.id.shstmname);
        stdob=(EditText)findViewById(R.id.shstdob);
        stschname=(EditText)findViewById(R.id.shstschoolname);
        stfquali=(EditText)findViewById(R.id.shstfquali);
        stfoccu=(EditText)findViewById(R.id.shstfoccu);
        stmquali=(EditText)findViewById(R.id.shstmquali);
        stmoccu=(EditText)findViewById(R.id.shstmoccu);
        stmob=(EditText)findViewById(R.id.shstmobno);
        stwtsp=(EditText)findViewById(R.id.shstwtspno);
        stfmob=(EditText)findViewById(R.id.shstfmobno);
        stfwtsp=(EditText)findViewById(R.id.shstfwtspno);
        stmmob=(EditText)findViewById(R.id.shstmmobno);
        stmwtsp=(EditText)findViewById(R.id.shstmwtspno);
        stomob=(EditText)findViewById(R.id.shstomobno);
        stladdress=(EditText)findViewById(R.id.shstladdress);
        stpaddress=(EditText)findViewById(R.id.shstpaddress);
        stambition=(EditText)findViewById(R.id.shstambition);
        stremark=(EditText)findViewById(R.id.shstremark);


        ((CircleImageView)findViewById(R.id.shstimage)).setImageBitmap(con(s.getStimage()));
        ((TextView)findViewById(R.id.shsid)).setText(" Student Id : "+s.getSid());
        ((EditText)findViewById(R.id.shstname)).setText(s.getStudentName());
        ((EditText)findViewById(R.id.shstfname)).setText(s.getStudentFatherName());
        ((EditText)findViewById(R.id.shstmname)).setText(s.getStudentMotherName());
        ((EditText)findViewById(R.id.shstdob)).setText(s.getStudentDOB());
        ((EditText)findViewById(R.id.shstclasses)).setText(s.getStudentClass());
        ((EditText)findViewById(R.id.shstcourse)).setText(s.getStudentCourse());
        ((EditText)findViewById(R.id.shstboard)).setText(s.getStudentBoard());
        ((EditText)findViewById(R.id.shstschoolname)).setText(s.getStudentSchoolname());
        ((EditText)findViewById(R.id.shstregdate)).setText(s.getStudentAdmissionDate());
        ((EditText)findViewById(R.id.shstfquali)).setText(s.getStudentFatherQualification());
        ((EditText)findViewById(R.id.shstfoccu)).setText(s.getStudentFatherOccupation());
        ((EditText)findViewById(R.id.shstmquali)).setText(s.getStudentMotherQualification());
        ((EditText)findViewById(R.id.shstmoccu)).setText(s.getStudentMotherOccupation());
        ((EditText)findViewById(R.id.shstmobno)).setText(s.getStudentMobNo());
        ((EditText)findViewById(R.id.shstwtspno)).setText(s.getStudentWtspNo());
        ((EditText)findViewById(R.id.shstfmobno)).setText(s.getStudentfMobNo());
        ((EditText)findViewById(R.id.shstfwtspno)).setText(s.getStudentfWtspNo());
        ((EditText)findViewById(R.id.shstmmobno)).setText(s.getStudentmMobNo());
        ((EditText)findViewById(R.id.shstmwtspno)).setText(s.getStudentmWtspNo());
        ((EditText)findViewById(R.id.shstomobno)).setText(s.getStudentOtherMobNo());
        ((EditText)findViewById(R.id.shstladdress)).setText(s.getStudentLoacalAddress());
        ((EditText)findViewById(R.id.shstpaddress)).setText(s.getStudentPermanentAddress());
        ((EditText)findViewById(R.id.shstambition)).setText(s.getStudentAmbitions());
        ((EditText)findViewById(R.id.shstremark)).setText(s.getStudentRemark());


        stimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] colors = {"Pick from Gallery", "Remove image"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowStudentDetails.this);
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
                            flag=true;
                        }
                    }
                });
                builder.show();
            }
        });




        if (activityId==1)
        {

            ((EditText)findViewById(R.id.shstname)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfname)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmname)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstdob)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstclasses)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstcourse)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstboard)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstschoolname)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstregdate)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfquali)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfoccu)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmquali)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmoccu)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmobno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstwtspno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfmobno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfwtspno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmmobno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstmwtspno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstomobno)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstladdress)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstpaddress)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstambition)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstremark)).setKeyListener(null);

        }else if (activityId==2)
        {
            ((Button)findViewById(R.id.shadtost)).setText("Update Information");
            ((EditText)findViewById(R.id.shstname)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstfname)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstmname)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstdob)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstclasses)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstcourse)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstboard)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstschoolname)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstregdate)).setKeyListener(null);
            ((EditText)findViewById(R.id.shstfquali)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstfoccu)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstmquali)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstmoccu)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstmobno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstwtspno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstfmobno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstfwtspno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstmmobno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstmwtspno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstomobno)).setKeyListener(keyListenern);
            ((EditText)findViewById(R.id.shstladdress)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstpaddress)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstambition)).setKeyListener(keyListener);
            ((EditText)findViewById(R.id.shstremark)).setKeyListener(keyListener);

        }
        findViewById(R.id.shadtost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activityId==1)
                {

                    if (flag2) {
                        Toast.makeText(getApplicationContext(), "Loading... Please Wait.", Toast.LENGTH_LONG).show();
                        try {

                            PDDocument document = new PDDocument();
                            PDPage page = new PDPage(PDRectangle.A4);
                            document.addPage(page);
                            PDPageContentStream contentStream = new PDPageContentStream(document, page);
                            contentStream.beginText();
                            contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
                            //Setting the leading
                            contentStream.setLeading(14.5f);
                            //Setting the position for the line
                            contentStream.newLineAtOffset(25, 800);

                            //Adding text in the form of string
                            contentStream.showText("                                       |  |  |  MATHS VISION  |  |  |");
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("          * * * * * * * * * * * * * Student's Details * * * * * * * * * * * * *");
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Student id :- " + s.getSid());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Name :- " + s.getStudentName());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Father Name :- " + s.getStudentFatherName());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Mother Name :- " + s.getStudentMotherName());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Date of Birth :- " + s.getStudentDOB());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Class :- " + s.getStudentClass());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("School :- " + s.getStudentSchoolname());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Board :- " + s.getStudentBoard());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Course :- " + s.getStudentCourse());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Registration date :- " + s.getStudentAdmissionDate());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("          * * * * * * * * * * * * * Parent's Details * * * * * * * * * * * * *");
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Father's Qualification :- " + s.getStudentFatherQualification());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Father's Occupation :- " + s.getStudentFatherOccupation());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Mother's Qualification :- " + s.getStudentMotherQualification());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Mother's Occupation :- " + s.getStudentMotherOccupation());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("          * * * * * * * * * * * * * Contact Details * * * * * * * * * * * * *");
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Student :  Mobile No. :- " + s.getStudentMobNo() + "           Whatsapp No. :- " + s.getStudentWtspNo());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Father :  Mobile No. :- " + s.getStudentfMobNo() + "           Whatsapp No. :- " + s.getStudentfWtspNo());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Mother :  Mobile No. :- " + s.getStudentmMobNo() + "          Whatsapp No. :- " + s.getStudentmWtspNo());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Permanent Adress :- " + s.getStudentPermanentAddress());
                            contentStream.newLine();
                            contentStream.newLine();
                            contentStream.showText("Local Adress :- " + s.getStudentLoacalAddress());


                            //Ending the content stream
                            contentStream.endText();
                            contentStream.close();
                            document.save(Environment.getExternalStorageDirectory().getPath() + "/Download/" + s.getStudentName() + "-" + s.getSid() + ".pdf");
                            document.close();

                            Toast.makeText(getApplicationContext(), "Pdf Created Successfully", Toast.LENGTH_LONG).show();
                            flag2=false;
                            ((Button)findViewById(R.id.shadtost)).setText("Share Pdf");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else
                    {
                        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                        File fileWithinMyDir = new File(Environment.getExternalStorageDirectory().getPath() + "/Download/" + s.getStudentName() + "-" + s.getSid() + ".pdf");
                        if(fileWithinMyDir.exists()) {
                            intentShareFile.setType("application/pdf");
                            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Download/" + s.getStudentName() + "-" + s.getSid() + ".pdf"));
                            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Sharing File...");
                            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing File...");
                            startActivity(Intent.createChooser(intentShareFile, "Share File"));
                        }
                    }

                }
                else
                {

                    if (checkEmpty()==0)
                    {
                        StudentInformation updt=new StudentInformation();
                        updt.setSid(s.getSid());
                        updt.setStudentName(stname.getText().toString());
                        updt.setStudentFatherName(stfname.getText().toString());
                        updt.setStudentMotherName(stmname.getText().toString());
                        updt.setStudentDOB(stdob.getText().toString());
                        updt.setStudentClass(((EditText)findViewById(R.id.shstclasses)).getText().toString());
                        updt.setStudentBoard(((EditText)findViewById(R.id.shstboard)).getText().toString());
                        updt.setStudentCourse(((EditText)findViewById(R.id.shstcourse)).getText().toString());
                        updt.setStudentSchoolname(stschname.getText().toString());
                        if (stfquali.getText().toString().matches(""))
                            updt.setStudentFatherQualification("Null");
                        else
                            updt.setStudentFatherQualification(stfquali.getText().toString());


                        if (stmquali.getText().toString().matches(""))
                            updt.setStudentMotherQualification("Null");
                        else
                            updt.setStudentMotherQualification(stmquali.getText().toString());


                        if (stfoccu.getText().toString().matches(""))
                            updt.setStudentFatherOccupation("Null");
                        else
                            updt.setStudentFatherOccupation(stfoccu.getText().toString());

                        if (stmoccu.getText().toString().matches(""))
                            updt.setStudentMotherOccupation("Null");
                        else
                            updt.setStudentMotherOccupation(stmoccu.getText().toString());

                        updt.setStudentMobNo(stmob.getText().toString());
                        updt.setStudentfMobNo(stfmob.getText().toString());
                        updt.setStudentmMobNo(stmmob.getText().toString());

                        if (stwtsp.getText().toString().matches(""))
                            updt.setStudentWtspNo("Null");
                        else
                            updt.setStudentWtspNo(stwtsp.getText().toString());


                        if (stfwtsp.getText().toString().matches(""))
                            updt.setStudentfWtspNo("Null");
                        else
                            updt.setStudentfWtspNo(stfwtsp.getText().toString());


                        if (stmwtsp.getText().toString().matches(""))
                            updt.setStudentmWtspNo("Null");
                        else
                            updt.setStudentmWtspNo(stmwtsp.getText().toString());

                        if (((EditText)findViewById(R.id.shstomobno)).getText().toString().matches(""))
                            updt.setStudentOtherMobNo("Null");
                        else
                            updt.setStudentOtherMobNo(((EditText)findViewById(R.id.shstomobno)).getText().toString());

                        if (stladdress.getText().toString().matches(""))
                            updt.setStudentLoacalAddress("Null");
                        else
                            updt.setStudentLoacalAddress(stladdress.getText().toString());

                        if (stpaddress.getText().toString().matches(""))
                            updt.setStudentPermanentAddress("Null");
                        else
                            updt.setStudentPermanentAddress(stpaddress.getText().toString());

                        if (stambition.getText().toString().matches(""))
                            updt.setStudentAmbitions("Null");
                        else
                            updt.setStudentAmbitions(stambition.getText().toString());

                        if (stremark.getText().toString().matches(""))
                            updt.setStudentRemark("Null");
                        else
                            updt.setStudentRemark(stremark.getText().toString());
                        updt.setStudentAdmissionDate(((EditText)findViewById(R.id.shstregdate)).getText().toString());

                        if (flag)
                        {
                            stimg.setDrawingCacheEnabled(true);
                            stimg.buildDrawingCache();
                            Bitmap bitmap = stimg.getDrawingCache();
                            ByteArrayOutputStream baos=new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                            byte[] data = baos.toByteArray();
                            updt.setStimage(data);

                        }else updt.setStimage(s.getStimage());

                        if (isConnected())
                        {

                            if (new DataBaseHelper(ShowStudentDetails.this).setStudentInfo(updt,1)!=-1&&new FirebaseHelper().addStudent(updt))
                            {
                                alert(1);

                            }else
                                alert(6);
                        }
                        else alert(5);
                    }

                }
            }
        });


    }
    public Bitmap con(byte[] image)
    {
        return  BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public void removeError()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ((EditText)findViewById(R.id.shstomobno)).setError(null);
                ((EditText)findViewById(R.id.shstmwtspno)).setError(null);
                ((EditText)findViewById(R.id.shstfwtspno)).setError(null);
                ((EditText)findViewById(R.id.shstwtspno)).setError(null);
            }
        },2000);
    }


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
        else if (stladdress.getText().toString().matches("")&&stpaddress.getText().toString().matches(""))
            return alert(4);
        else
            return 0;
    }


    public int alert(int n) {
        AlertDialog alertDialog = new AlertDialog.Builder(ShowStudentDetails.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Information Updated Successfully....");
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
                    flag=true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
