package com.example.mathsvision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;

public class BackupRestore extends AppCompatActivity {

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    DataBaseHelper db;
    ImageView stimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_restore);
        getSupportActionBar().hide();
        stimg=(ImageView)findViewById(R.id.stimage2);
        ((ImageView)findViewById(R.id.stimage2)).setVisibility(View.INVISIBLE);
        db=new DataBaseHelper(BackupRestore.this);
        findViewById(R.id.enqbackup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getEnquiry().getCount()==0)
                {
                    if (isConnected()) {
                        myRef.child("Enquiry").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    String address = dataSnapshot1.child("address").getValue().toString();
                                    String board = dataSnapshot1.child("board").getValue().toString();
                                    String clas = dataSnapshot1.child("class").getValue().toString();
                                    String eid = dataSnapshot1.child("eid").getValue().toString();
                                    String eqdate = dataSnapshot1.child("eqdate").getValue().toString();
                                    String fname = dataSnapshot1.child("fname").getValue().toString();
                                    String mobilno = dataSnapshot1.child("mobilno").getValue().toString();
                                    String name = dataSnapshot1.child("name").getValue().toString();
                                    String reference = dataSnapshot1.child("reference").getValue().toString();
                                    String remark = dataSnapshot1.child("remark").getValue().toString();
                                    String school = dataSnapshot1.child("school").getValue().toString();
                                    String whatsappno = dataSnapshot1.child("whatsappno").getValue().toString();
                                    db.setEnquiry(eid, name, fname, clas, school, board, mobilno, whatsappno, address, reference, remark, eqdate, 0);
                                }
                                Toast.makeText(getApplicationContext(), "Data Restored Successfully", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else alert();
                }else // end of if
                    Toast.makeText(getApplicationContext(),"Data Backup Not Possible",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.stbackup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getStudentInfo().getCount()==0)
                {
                    if (isConnected()) {
                        myRef.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    StudentInformation s = new StudentInformation();
                                    s.setStudentAdmissionDate(dataSnapshot1.child("admisndate").getValue().toString());
                                    s.setStudentAmbitions(dataSnapshot1.child("ambition").getValue().toString());
                                    s.setStudentBoard(dataSnapshot1.child("board").getValue().toString());
                                    s.setStudentClass(dataSnapshot1.child("class").getValue().toString());
                                    s.setStudentCourse(dataSnapshot1.child("course").getValue().toString());
                                    s.setStudentDOB(dataSnapshot1.child("dob").getValue().toString());
                                    s.setStudentFatherName(dataSnapshot1.child("fathername").getValue().toString());
                                    s.setStudentfMobNo(dataSnapshot1.child("fmobno").getValue().toString());
                                    s.setStudentFatherOccupation(dataSnapshot1.child("foccupation").getValue().toString());
                                    s.setStudentFatherQualification(dataSnapshot1.child("fqualification").getValue().toString());
                                    s.setStudentfWtspNo(dataSnapshot1.child("fwtspno").getValue().toString());
                                    s.setStudentLoacalAddress(dataSnapshot1.child("localadd").getValue().toString());
                                    s.setStudentmMobNo(dataSnapshot1.child("mmobno").getValue().toString());
                                    s.setStudentMotherOccupation(dataSnapshot1.child("moccupation").getValue().toString());
                                    s.setStudentMotherName(dataSnapshot1.child("mothername").getValue().toString());
                                    s.setStudentMotherQualification(dataSnapshot1.child("mqualification").getValue().toString());
                                    s.setStudentmWtspNo(dataSnapshot1.child("mwtspno").getValue().toString());
                                    s.setStudentName(dataSnapshot1.child("name").getValue().toString());
                                    s.setStudentOtherMobNo(dataSnapshot1.child("omobno").getValue().toString());
                                    s.setStudentPermanentAddress(dataSnapshot1.child("peradd").getValue().toString());
                                    s.setStudentRemark(dataSnapshot1.child("remark").getValue().toString());
                                    s.setStudentSchoolname(dataSnapshot1.child("schoolname").getValue().toString());
                                    s.setSid(dataSnapshot1.child("sid").getValue().toString());
                                    s.setStudentMobNo(dataSnapshot1.child("smobno").getValue().toString());
                                    s.setStudentWtspNo(dataSnapshot1.child("swtspno").getValue().toString());

                                    stimg.setDrawingCacheEnabled(true);
                                    stimg.buildDrawingCache();
                                    Bitmap bitmap = stimg.getDrawingCache();
                                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                                    byte[] data = baos.toByteArray();
                                    s.setStimage(data);

                                    db.setStudentInfo(s,0);

                                }
                                Toast.makeText(getApplicationContext(), "Data Restored Successfully", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }else alert();
                }else // end of if
                    Toast.makeText(getApplicationContext(),"Data Backup Not Possible",Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean isConnected()
    {

        ConnectivityManager c=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo n = c.getActiveNetworkInfo();
        return n!=null && n.isConnected();

    }
    public void alert() {
        AlertDialog alertDialog = new AlertDialog.Builder(BackupRestore.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Turn on your Internet Connection");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
}
