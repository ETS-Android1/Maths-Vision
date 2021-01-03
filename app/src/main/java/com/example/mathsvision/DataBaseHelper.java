package com.example.mathsvision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbnam = "MathsVision.db";
    public static final String logintab = "login";
    public static final String enquirytab = "enquiry";
    public static final String studentinfotab = "studentinfo";
    public static final String col1 = "ID";
    public static final String username = "username";
    public static final String password = "password";
    public static final String name = "name";
    public static final String adhar = "adhar";
    public static final String mobile = "mobile";
    public static final String status = "status";

    // ************************** ENQUIRY TABLE COLUMN NAMES ********************************************
    public static final String eid = "eid";
    public static final String eqstname = "eqstname";
    public static final String eqstfname = "eqstfname";
    public static final String eqclasstojoin = "eqclasstojoin";
    public static final String eqschoolname = "eqschoolname";
    public static final String eqboard = "eqboard";
    public static final String eqdate = "eqdate";
    public static final String eqmobno = "eqmobno";
    public static final String eqwhatsappno = "eqwhatsappno";
    public static final String eqaddress = "eqaddress";
    public static final String eqreferences = "eqreferences";
    public static final String eqremarks = "eqremarks";

    // ************************** STUDENT INFO. TABLE COLUMN NAMES ********************************************

    public static final String stimage = "stimage";
    public static final String sid = "sid";
    public static final String stname = "stname";
    public static final String stfname = "stfname";
    public static final String stmname = "stmname";
    public static final String stdob = "stdob";
    public static final String stboard = "stboard";
    public static final String stclass = "stclass";
    public static final String stcourse = "stcourse";
    public static final String stschoolname = "stschoolname";
    public static final String stfquali = "stfquali";
    public static final String stfoccu = "stfoccu";
    public static final String stmquali = "stmquali";
    public static final String stmoccu = "stmoccu";
    public static final String stmobno = "stmobno";
    public static final String stwtspno = "stwtspno";
    public static final String stfmobno = "stfmobno";
    public static final String stfwtspno = "stfwtspno";
    public static final String stmmobno = "stmmobno";
    public static final String stmwtspno = "stmwtspno";
    public static final String stothermobno = "stothermobno";
    public static final String stladdress = "stladdress";
    public static final String stpaddress = "stpaddress";
    public static final String stambition = "stambition";
    public static final String stremark = "stremark";
    public static final String stadmsdate = "stadmsdate";


    public DataBaseHelper(@Nullable Context context) {
        super(context, dbnam, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE login (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,name TEXT,password TEXT,adhar TEXT,mobile TEXT,status TEXT)");
        db.execSQL("CREATE TABLE "+enquirytab+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,eqdate TEXT, eqstname TEXT,eqstfname TEXT,eqclasstojoin TEXT,eqschoolname TEXT,eqboard TEXT,eqmobno TEXT,eqwhatsappno TEXT,eqaddress TEXT,eqreferences TEXT,eqremarks TEXT,eid TEXT UNIQUE)");
        db.execSQL("CREATE TABLE studentinfo(stadmsdate TEXT,stimage BLOB,sid TEXT PRIMARY KEY,stname TEXT,stfname TEXT,stmname TEXT,stdob TEXT,stboard TEXT,stclass TEXT,stcourse TEXT,stschoolname TEXT,stfquali TEXT,stfoccu TEXT,stmquali TEXT,stmoccu TEXT,stmobno TEXT,stwtspno TEXT,stfmobno TEXT,stfwtspno TEXT,stmmobno TEXT,stmwtspno TEXT,stothermobno TEXT,stladdress TEXT,stpaddress TEXT,stambition TEXT,stremark TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + logintab);
        db.execSQL(" DROP TABLE IF EXISTS " + enquirytab);
        db.execSQL(" DROP TABLE IF EXISTS " + studentinfotab);


        onCreate(db);
    }
    public long setStatus(String s)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("status",s);
        Cursor c = this.getLogin();
        c.moveToFirst();
        String user = c.getString(c.getColumnIndex(DataBaseHelper.username));

        return db.update("login",cv,"username = ?",new String[]{user});
    }

    public long setLogin(String user,String password,String name,String adhar,String mobile)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("username",user);
        cv.put("password",password);
        cv.put("name",name);
        cv.put("mobile",mobile);
        cv.put("adhar",adhar);
       return db.insert("login",null,cv);
    }
    public Cursor getLogin()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+logintab,null);
        return c;

    }

    public long setEnquiry(String eid,String eqstname,String eqstfname,String eqclasstojoin,String eqschoolname,String eqboard,String eqmobno,String eqwhatsappno,String eqaddress,String eqreferences, String eqremarks,String eqdate,int n)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(DataBaseHelper.eid,eid);
        cv.put(DataBaseHelper.eqstname,eqstname);
        cv.put(DataBaseHelper.eqstfname,eqstfname);
        cv.put(DataBaseHelper.eqclasstojoin,eqclasstojoin);
        cv.put(DataBaseHelper.eqschoolname,eqschoolname);
        cv.put(DataBaseHelper.eqboard,eqboard);
        cv.put(DataBaseHelper.eqmobno,eqmobno);
        cv.put(DataBaseHelper.eqwhatsappno,eqwhatsappno);
        cv.put(DataBaseHelper.eqaddress,eqaddress);
        cv.put(DataBaseHelper.eqreferences,eqreferences);
        cv.put(DataBaseHelper.eqremarks,eqremarks);
        cv.put(DataBaseHelper.eqdate,eqdate);
        if (n==0)
        return db.insert(enquirytab,null,cv);
        else
            return db.update(enquirytab,cv,"eid = ?",new String[]{eid});
    }
    public Cursor getEnquiry()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+enquirytab,null);
        return c;

    }
    public Cursor getConditionEnquiry(String column,String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+enquirytab+" where "+column+" = '"+value+"'",null);
        return c;

    }
    public boolean deleteData(String table,String id,int n)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (n==0)
        return db.delete(table,"eid = ?",new String[]{id})>0;
        else
            return db.delete(table,"sid = ?",new String[]{id})>0;
    }

    public long setStudentInfo(StudentInformation s,int n)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(DataBaseHelper.stimage,s.getStimage());
        cv.put(DataBaseHelper.sid,s.getSid());
        cv.put(DataBaseHelper.stname,s.getStudentName());
        cv.put(DataBaseHelper.stfname,s.getStudentFatherName());
        cv.put(DataBaseHelper.stmname,s.getStudentMotherName());
        cv.put(DataBaseHelper.stdob,s.getStudentDOB());
        cv.put(DataBaseHelper.stboard,s.getStudentBoard());
        cv.put(DataBaseHelper.stclass,s.getStudentClass());
        cv.put(DataBaseHelper.stcourse,s.getStudentCourse());
        cv.put(DataBaseHelper.stschoolname,s.getStudentSchoolname());
        cv.put(DataBaseHelper.stfquali,s.getStudentFatherQualification());
        cv.put(DataBaseHelper.stfoccu,s.getStudentFatherOccupation());
        cv.put(DataBaseHelper.stmquali,s.getStudentMotherQualification());
        cv.put(DataBaseHelper.stmoccu,s.getStudentMotherOccupation());
        cv.put(DataBaseHelper.stmobno,s.getStudentMobNo());
        cv.put(DataBaseHelper.stwtspno,s.getStudentWtspNo());
        cv.put(DataBaseHelper.stfmobno,s.getStudentfMobNo());
        cv.put(DataBaseHelper.stfwtspno,s.getStudentfWtspNo());
        cv.put(DataBaseHelper.stmmobno,s.getStudentmMobNo());
        cv.put(DataBaseHelper.stmwtspno,s.getStudentmWtspNo());
        cv.put(DataBaseHelper.stothermobno,s.getStudentOtherMobNo());
        cv.put(DataBaseHelper.stladdress,s.getStudentLoacalAddress());
        cv.put(DataBaseHelper.stpaddress,s.getStudentPermanentAddress());
        cv.put(DataBaseHelper.stambition,s.getStudentAmbitions());
        cv.put(DataBaseHelper.stremark,s.getStudentRemark());
        cv.put(DataBaseHelper.stadmsdate,s.getStudentAdmissionDate());
        if (n==0)
            return db.insert(studentinfotab,null,cv);
        else
            return db.update(studentinfotab,cv,"sid = ?",new String[]{s.getSid()});
    }

    public Cursor getConditionStudentInfo(String column,String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+studentinfotab+" where "+column+" = '"+value+"'",null);
        return c;

    }


    public Cursor getStudentInfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+studentinfotab,null);
        return c;

    }

}
