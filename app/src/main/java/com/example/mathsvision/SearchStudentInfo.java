package com.example.mathsvision;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchStudentInfo extends AppCompatActivity  {

    String[] searchby = { "Search by","All enquiries", "Student Name", "Father's Name", "Class", "School Name", "Board","Mobile No."};
    KeyListener keyListener;
    RecyclerView recyclerView;
    DataBaseHelper db;
    int activityId,pos;
    ArrayList<String> sid,stname,stfname,stmname,stdob,stboard,stclass,stcourse,stschname,stfquali,stfoccu,stmquali,stmoccu,stmob,stwtsp,stfmob,stfwtsp,stmmob,stmwtsp,stomob,stladdress,stpaddress,stambition,stremark,stadmsndate;
    ArrayList<byte[]>stimage;
    Cursor c;
    CustomAdapterSt customAdapterSt;
    ImageView delimg;
    private CustomAdapterSt.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_info);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.recyclervs);
        delimg=(ImageView) findViewById(R.id.deletenq4);
        activityId=getIntent().getExtras().getInt("Id");


        if (activityId==2) {
            ((TextView) findViewById(R.id.textView17)).setText("*  *  *  *  *  Edit Enquiry  *  *  *  *  *");
            delimg.setVisibility(View.INVISIBLE);
        }else if (activityId==3)
        {
            ((TextView)findViewById(R.id.textView17)).setText("*  *  *  *  *  Delete Enquiry  *  *  *  *  *");
            delimg.setVisibility(View.VISIBLE);
        }
        else
            delimg.setVisibility(View.INVISIBLE);

        stimage=new ArrayList<>();
        sid=new ArrayList<>();
        stname=new ArrayList<>();
        stfname=new ArrayList<>();
        stmname=new ArrayList<>();
        stdob=new ArrayList<>();
        stboard=new ArrayList<>();
        stclass=new ArrayList<>();
        stcourse=new ArrayList<>();
        stschname=new ArrayList<>();
        stfquali=new ArrayList<>();
        stfoccu=new ArrayList<>();
        stmquali=new ArrayList<>();
        stmoccu=new ArrayList<>();
        stmob=new ArrayList<>();
        stwtsp=new ArrayList<>();
        stfmob=new ArrayList<>();
        stfwtsp=new ArrayList<>();
        stmmob=new ArrayList<>();
        stmwtsp=new ArrayList<>();
        stomob=new ArrayList<>();
        stladdress=new ArrayList<>();
        stpaddress=new ArrayList<>();
        stambition=new ArrayList<>();
        stremark=new ArrayList<>();
        stadmsndate=new ArrayList<>();

        keyListener=((EditText)findViewById(R.id.searchbyt2)).getKeyListener();
        ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(null);
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,searchby);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.spinner2)).setAdapter(a);
        ((Spinner)findViewById(R.id.spinner2)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((EditText)findViewById(R.id.searchbyt2)).setError(null);
                if (position==0) {
                    clearList();
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("");
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Search by");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.INVISIBLE);
                }
                if (position==1) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("All Enquiries");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==2) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(keyListener);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("");
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Student Name");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==3) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(keyListener);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("");
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Father's Name");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==4) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Class");
                    String[] classes = {"12th", "11th", "10th","9th","8th","7th"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchStudentInfo.this);
                    builder.setTitle("Select Class");
                    builder.setItems(classes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("12th");
                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("11th");
                            }else if (which==2)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("10th");
                            }else if (which==3)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("9th");
                            }else if (which==4)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("8th");
                            }else if (which==5)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("7th");
                            }
                        }
                    });
                    builder.show();
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==5) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(keyListener);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("");
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("School Name");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==6) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Board");
                    String[] board = {"MP Board", "CBSE Board"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchStudentInfo.this);
                    builder.setTitle("Select Board");
                    builder.setItems(board, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("MP Board");
                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.searchbyt2)).setText("CBSE Board");
                            }
                        }
                    });
                    builder.show();
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
                if (position==7) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt2)).setKeyListener(keyListener);
                    ((EditText)findViewById(R.id.searchbyt2)).setInputType(InputType.TYPE_CLASS_NUMBER);
                    ((EditText)findViewById(R.id.searchbyt2)).setText("");
                    ((EditText)findViewById(R.id.searchbyt2)).setHint("Mobile No.");
                    ((Button) findViewById(R.id.eqsearch2)).setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.eqsearch2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                if(checkEmpty()==0) {
                    db = new DataBaseHelper(SearchStudentInfo.this);
                    if (pos == 1) {
                        c = db.getStudentInfo();

                    }else if (pos==2)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stname,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        //   setRecyclerView("name",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }
                    else if (pos==3)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stfname,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        // setRecyclerView("fname",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==4)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stclass,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        //setRecyclerView("class",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==5)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stschoolname,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        //setRecyclerView("school",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==6)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stboard,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        //setRecyclerView("board",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==7)
                    {
                        c=db.getConditionStudentInfo(DataBaseHelper.stmobno,((EditText)findViewById(R.id.searchbyt2)).getText().toString());
                        //setRecyclerView("mobilno",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }

                    if (c.moveToFirst() && c.getCount() != 0) {
                        do {
                            stimage.add(c.getBlob(c.getColumnIndex(DataBaseHelper.stimage)));
                            sid.add(c.getString(c.getColumnIndex(DataBaseHelper.sid)));
                            stname.add(c.getString(c.getColumnIndex(DataBaseHelper.stname)));
                            stfname.add(c.getString(c.getColumnIndex(DataBaseHelper.stfname)));
                            stmname.add(c.getString(c.getColumnIndex(DataBaseHelper.stmname)));
                            stdob.add(c.getString(c.getColumnIndex(DataBaseHelper.stdob)));
                            stboard.add(c.getString(c.getColumnIndex(DataBaseHelper.stboard)));
                            stclass.add(c.getString(c.getColumnIndex(DataBaseHelper.stclass)));
                            stcourse.add(c.getString(c.getColumnIndex(DataBaseHelper.stcourse)));
                            stschname.add(c.getString(c.getColumnIndex(DataBaseHelper.stschoolname)));
                            stfquali.add(c.getString(c.getColumnIndex(DataBaseHelper.stfquali)));
                            stfoccu.add(c.getString(c.getColumnIndex(DataBaseHelper.stfoccu)));
                            stmquali.add(c.getString(c.getColumnIndex(DataBaseHelper.stmquali)));
                            stmoccu.add(c.getString(c.getColumnIndex(DataBaseHelper.stmoccu)));
                            stmob.add(c.getString(c.getColumnIndex(DataBaseHelper.stmobno)));
                            stwtsp.add(c.getString(c.getColumnIndex(DataBaseHelper.stwtspno)));
                            stfmob.add(c.getString(c.getColumnIndex(DataBaseHelper.stfmobno)));
                            stfwtsp.add(c.getString(c.getColumnIndex(DataBaseHelper.stfwtspno)));
                            stmmob.add(c.getString(c.getColumnIndex(DataBaseHelper.stmmobno)));
                            stmwtsp.add(c.getString(c.getColumnIndex(DataBaseHelper.stmwtspno)));
                            stomob.add(c.getString(c.getColumnIndex(DataBaseHelper.stothermobno)));
                            stladdress.add(c.getString(c.getColumnIndex(DataBaseHelper.stladdress)));
                            stpaddress.add(c.getString(c.getColumnIndex(DataBaseHelper.stpaddress)));
                            stambition.add(c.getString(c.getColumnIndex(DataBaseHelper.stambition)));
                            stremark.add(c.getString(c.getColumnIndex(DataBaseHelper.stremark)));
                            stadmsndate.add(c.getString(c.getColumnIndex(DataBaseHelper.stadmsdate)));

                        } while (c.moveToNext());
                       setOnClickListener();
                        customAdapterSt = new CustomAdapterSt(SearchStudentInfo.this,stfname,stname,listener,stclass,sid,activityId,stimage);
                        recyclerView.setAdapter(customAdapterSt);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchStudentInfo.this));

                    } else alert(1);

                }
            }
        });

        findViewById(R.id.deletenq4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataBaseHelper(SearchStudentInfo.this);
                if (CustomAdapterSt.delId.size()==0)
                    Toast.makeText(getApplicationContext(), "Information not Selected", Toast.LENGTH_LONG).show();
                else {
                    if (isConnected()) {
                        for (int i = 0; i < CustomAdapterSt.delId.size(); i++) {
                            db.deleteData(DataBaseHelper.studentinfotab, CustomAdapterSt.delId.get(i),1);
                            new FirebaseHelper().deleteStudent(CustomAdapterSt.delId.get(i));
                        }
                        CustomAdapterSt.delId.clear();
                        recyclerView.setAdapter(null);
                        Toast.makeText(getApplicationContext(), "Information Deleted", Toast.LENGTH_LONG).show();

                    }else alert(2);
                }
            }
        });

    }//end of on create method...

    private int checkEmpty() {

        if (pos==2&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("Student Name is Empty");
            return 1;
        }else if (pos==3&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("Father's Name is Empty");
            return 1;
        }else if (pos==4&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("Class is Empty");
            return 1;
        }else if (pos==5&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("School Name is Empty");
            return 1;
        }else if (pos==6&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("Board is Empty");
            return 1;
        }else if (pos==7&&((EditText)findViewById(R.id.searchbyt2)).getText().toString().matches("")) {
            ((EditText)findViewById(R.id.searchbyt2)).setError("Mobile No. is Empty");
            return 1;
        }
        else return 0;
    }
    private void clearList() {

        recyclerView.setAdapter(null);
        stimage.clear();
        sid.clear();
        stname.clear();
        stfname.clear();
        stmname.clear();
        stdob.clear();
        stboard.clear();
        stclass.clear();
        stcourse.clear();
        stschname.clear();
        stfquali.clear();
        stfoccu.clear();
        stmquali.clear();
        stmoccu.clear();
        stmob.clear();
        stwtsp.clear();
        stfmob.clear();
        stfwtsp.clear();
        stmmob.clear();
        stmwtsp.clear();
        stomob.clear();
        stladdress.clear();
        stpaddress.clear();
        stambition.clear();
        stremark.clear();
        stadmsndate.clear();


    }
    private void setOnClickListener() {
        if (activityId!=3) {
            listener = new CustomAdapterSt.RecyclerViewClickListener() {
                @Override
                public void onClick(View v, int position) {

                    StudentInformation s = new StudentInformation();
                    s.setStimage(stimage.get(position));
                    s.setSid(sid.get(position));
                    s.setStudentName(stname.get(position));
                    s.setStudentFatherName(stfname.get(position));
                    s.setStudentMotherName(stmname.get(position));
                    s.setStudentDOB(stdob.get(position));
                    s.setStudentBoard(stboard.get(position));
                    s.setStudentClass(stclass.get(position));
                    s.setStudentCourse(stcourse.get(position));
                    s.setStudentSchoolname(stschname.get(position));
                    s.setStudentFatherQualification(stfquali.get(position));
                    s.setStudentFatherOccupation(stfoccu.get(position));
                    s.setStudentMotherQualification(stmquali.get(position));
                    s.setStudentMotherOccupation(stmoccu.get(position));
                    s.setStudentMobNo(stmob.get(position));
                    s.setStudentWtspNo(stwtsp.get(position));
                    s.setStudentfMobNo(stfmob.get(position));
                    s.setStudentfWtspNo(stfwtsp.get(position));
                    s.setStudentmMobNo(stmmob.get(position));
                    s.setStudentmWtspNo(stmwtsp.get(position));
                    s.setStudentOtherMobNo(stomob.get(position));
                    s.setStudentLoacalAddress(stladdress.get(position));
                    s.setStudentPermanentAddress(stpaddress.get(position));
                    s.setStudentAmbitions(stambition.get(position));
                    s.setStudentRemark(stremark.get(position));
                    s.setStudentAdmissionDate(stadmsndate.get(position));

                    Intent i = new Intent(SearchStudentInfo.this, ShowStudentDetails.class);
                    i.putExtra("Id", activityId);
                    i.putExtra("std",(Serializable) s);
                    startActivity(i);
                    if (activityId == 2)
                        recyclerView.setAdapter(null);

                }
            };
        }
    }
    public int alert(int n) {
        AlertDialog alertDialog = new AlertDialog.Builder(SearchStudentInfo.this).create();
        alertDialog.setTitle("Alert");
         if (n==1)
            alertDialog.setMessage("Information Not Available...");
        else if (n==2)
            alertDialog.setMessage("Please turn on your internet connection...!!");

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
