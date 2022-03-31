package com.example.mathsvision;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchStudentInfo extends AppCompatActivity {
    String[] searchby = { "Search by","All enquiries", "Student Name", "Father's Name", "Class", "School Name", "Board","Mobile No."};
    public int pos;
    KeyListener listener;
    RecyclerView recyclerView;
    DataBaseHelper db;
    int activityId;
    ArrayList<String> eid,name,fname,classes,board,school,mob,wtsp,address,ref,remarks,date;
    ValueEventListener valueEventListener;
    private Dialog loadingDialog;
    Cursor c;
    DatabaseReference eref;
    CustomAdapter customAdapter;
    private CustomAdapter.RecyclerViewClickListener listenere;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student_info);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerv);
        name=new ArrayList<>();
        img=(ImageView) findViewById(R.id.deletenq3);
        activityId=getIntent().getExtras().getInt("Id");



        if (activityId==2) {
            ((TextView) findViewById(R.id.textView9)).setText("*  *  *  *  *  Edit Enquiry  *  *  *  *  *");
            img.setVisibility(View.INVISIBLE);
        }else if (activityId==3)
        {
            ((TextView)findViewById(R.id.textView9)).setText("*  *  *  *  *  Delete Enquiry  *  *  *  *  *");
            img.setVisibility(View.VISIBLE);
        }
        else
            img.setVisibility(View.INVISIBLE);


            fname = new ArrayList<>();
            classes = new ArrayList<>();
            eid = new ArrayList<>();
            board = new ArrayList<>();
            school = new ArrayList<>();
            mob = new ArrayList<>();
            wtsp = new ArrayList<>();
            address = new ArrayList<>();
            ref = new ArrayList<>();
            remarks = new ArrayList<>();
            date = new ArrayList<>();

        ((TextView) findViewById(R.id.session2)).setText("Session : "+new GetSession().getSession());
        listener=((EditText)findViewById(R.id.searchbyt)).getKeyListener();
        ((EditText)findViewById(R.id.searchbyt)).setKeyListener(null);
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,searchby);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner)findViewById(R.id.spinner)).setAdapter(a);
        ((Spinner)findViewById(R.id.spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerView.setAdapter(null);
                if (position==0) {
                    clearList();
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt)).setText("");
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Search by");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.INVISIBLE);
                }
                if (position==1) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt)).setText("All Enquiries");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==2) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(listener);
                    ((EditText)findViewById(R.id.searchbyt)).setText("");
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Student Name");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==3) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(listener);
                    ((EditText)findViewById(R.id.searchbyt)).setText("");
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Father's Name");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==4) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Class");
                    String[] classes = {"12th", "11th", "10th","9th","8th","7th"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchStudentInfo.this);
                    builder.setTitle("Select Class");
                    builder.setItems(classes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("12th");
                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("11th");
                            }else if (which==2)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("10th");
                            }else if (which==3)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("9th");
                            }else if (which==4)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("8th");
                            }else if (which==5)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("7th");
                            }
                        }
                    });
                    builder.show();
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==5) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(listener);
                    ((EditText)findViewById(R.id.searchbyt)).setText("");
                    ((EditText)findViewById(R.id.searchbyt)).setHint("School Name");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==6) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(null);
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Board");
                    String[] board = {"MP Board", "CBSE Board"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchStudentInfo.this);
                    builder.setTitle("Select Board");
                    builder.setItems(board, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("MP Board");
                            }else if (which==1)
                            {
                                ((EditText)findViewById(R.id.searchbyt)).setText("CBSE Board");
                            }
                        }
                    });
                    builder.show();
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
                if (position==7) {
                    pos=position;
                    ((EditText)findViewById(R.id.searchbyt)).setKeyListener(listener);
                    ((EditText)findViewById(R.id.searchbyt)).setInputType(InputType.TYPE_CLASS_NUMBER);
                    ((EditText)findViewById(R.id.searchbyt)).setText("");
                    ((EditText)findViewById(R.id.searchbyt)).setHint("Mobile No.");
                    ((Button) findViewById(R.id.eqsearch)).setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        findViewById(R.id.eqsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                if(checkEmpty()==0) {
                    db = new DataBaseHelper(SearchStudentInfo.this);
                    if (pos == 1) {
                        c = db.getEnquiry();

                    }else if (pos==2)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqstname,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                     //   setRecyclerView("name",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }
                    else if (pos==3)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqstfname,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                       // setRecyclerView("fname",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==4)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqclasstojoin,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                        //setRecyclerView("class",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==5)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqschoolname,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                        //setRecyclerView("school",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==6)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqboard,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                        //setRecyclerView("board",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }else if (pos==7)
                    {
                        c=db.getConditionEnquiry(DataBaseHelper.eqmobno,((EditText)findViewById(R.id.searchbyt)).getText().toString());
                        //setRecyclerView("mobilno",((EditText)findViewById(R.id.searchbyt)).getText().toString());
                    }

                    if (c.moveToFirst() && c.getCount() != 0) {
                        do {
                            eid.add(c.getString(c.getColumnIndex(DataBaseHelper.eid)));
                            name.add(c.getString(c.getColumnIndex(DataBaseHelper.eqstname)));
                            fname.add(c.getString(c.getColumnIndex(DataBaseHelper.eqstfname)));
                            classes.add(c.getString(c.getColumnIndex(DataBaseHelper.eqclasstojoin)));
                            board.add(c.getString(c.getColumnIndex(DataBaseHelper.eqboard)));
                            school.add(c.getString(c.getColumnIndex(DataBaseHelper.eqschoolname)));
                            mob.add(c.getString(c.getColumnIndex(DataBaseHelper.eqmobno)));
                            wtsp.add(c.getString(c.getColumnIndex(DataBaseHelper.eqwhatsappno)));
                            address.add(c.getString(c.getColumnIndex(DataBaseHelper.eqaddress)));
                            ref.add(c.getString(c.getColumnIndex(DataBaseHelper.eqreferences)));
                            remarks.add(c.getString(c.getColumnIndex(DataBaseHelper.eqremarks)));
                            date.add(c.getString(c.getColumnIndex(DataBaseHelper.eqdate)));

                        } while (c.moveToNext());

                        setOnClickListener();
                        customAdapter = new CustomAdapter(SearchStudentInfo.this, fname, name, listenere, classes, eid,activityId);
                        recyclerView.setAdapter(customAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchStudentInfo.this));

                    } else alert(8);

                }
            }
        });


        findViewById(R.id.deletenq3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DataBaseHelper(SearchStudentInfo.this);
                if (CustomAdapter.delId.size()==0)
                    Toast.makeText(getApplicationContext(), "Information not Selected", Toast.LENGTH_LONG).show();
                else {
                    if (isConnected()) {
                        for (int i = 0; i < CustomAdapter.delId.size(); i++) {
                            db.deleteData(DataBaseHelper.enquirytab, CustomAdapter.delId.get(i),0);
                            new FirebaseHelper().deleteEnquiry(CustomAdapter.delId.get(i));
                        }
                        CustomAdapter.delId.clear();
                        recyclerView.setAdapter(null);
                        Toast.makeText(getApplicationContext(), "Information Deleted", Toast.LENGTH_LONG).show();

                    }else alert(9);
                }
            }
        });
    }

    public void setRecyclerView(String col,String val){

        loading();
        loadingDialog.show();
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    eid.add(snapshot.child("eid").getValue().toString());
                    name.add(snapshot.child("name").getValue().toString());
                    fname.add(snapshot.child("fname").getValue().toString());
                    classes.add(snapshot.child("class").getValue().toString());
                }

               setOnClickListener();
                customAdapter = new CustomAdapter(SearchStudentInfo.this, fname, name, listenere, classes, eid,activityId);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchStudentInfo.this));
                loadingDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        eref=new FirebaseHelper().getEnquiryReference();
        Query query=eref.orderByChild(col).equalTo(val);
        query.addValueEventListener(valueEventListener);
    }
    private void clearList() {
        recyclerView.setAdapter(null);
        name.clear();
        fname.clear();
        eid.clear();
        classes.clear();
        board.clear();
        school.clear();
        mob.clear();
        wtsp.clear();
        address.clear();
        ref.clear();
        remarks.clear();
        date.clear();
    }
    public int checkEmpty() {
        if (pos==2&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
           return alert(1);
        else if (pos==3&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
            return alert(2);
        else if (pos==4&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
            return alert(3);
        else if (pos==5&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
            return alert(4);
        else if (pos==6&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
            return alert(5);
        else if (pos==7&&((EditText)findViewById(R.id.searchbyt)).getText().toString().matches(""))
            return alert(6);
        else return 0;
    }

    public int alert(int n) {
        AlertDialog alertDialog = new AlertDialog.Builder(SearchStudentInfo.this).create();
        alertDialog.setTitle("Alert");
        if (n==1)
            alertDialog.setMessage("Enter Student Name.");
        else if (n==2)
            alertDialog.setMessage("Enter Father's Name.");
        else if (n==3)
            alertDialog.setMessage("Enter Class");
        else if (n==4)
            alertDialog.setMessage("Enter School name");
        else if (n==5)
            alertDialog.setMessage("Enter Board");
        else if (n==6)
            alertDialog.setMessage("Enter Mobile number");
        else if (n==7)
            alertDialog.setMessage("Enter Address");
        else if (n==8)
            alertDialog.setMessage("Enquiry Not Available...");
        else if (n==9)
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
    private void setOnClickListener() {
        if (activityId!=3) {
            listenere = new CustomAdapter.RecyclerViewClickListener() {
                @Override
                public void onClick(View v, int position) {
                    Intent i = new Intent(SearchStudentInfo.this, ShowEnquiryData.class);
                    i.putExtra("Id", activityId);
                    i.putExtra("eid", eid.get(position));
                    i.putExtra("name", name.get(position));
                    i.putExtra("fname", fname.get(position));
                    i.putExtra("classes", classes.get(position));
                    i.putExtra("date", date.get(position));
                    i.putExtra("schl", school.get(position));
                    i.putExtra("board", board.get(position));
                    i.putExtra("wtspno", wtsp.get(position));
                    i.putExtra("address", address.get(position));
                    i.putExtra("ref", ref.get(position));
                    i.putExtra("remark", remarks.get(position));
                    i.putExtra("mobno", mob.get(position));
                    startActivity(i);
                    if (activityId == 2)
                        recyclerView.setAdapter(null);
                }
            };
        }
    }

    public void loading() {
        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
    }
    public boolean isConnected()
    {

        ConnectivityManager c=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo n = c.getActiveNetworkInfo();
        return n!=null && n.isConnected();

    }

}