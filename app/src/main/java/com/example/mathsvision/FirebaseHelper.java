package com.example.mathsvision;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FirebaseHelper {
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference("Enquiry");
    DatabaseReference stRef=database.getReference("Student");

    public DatabaseReference getEnquiryReference()
{
    return myRef;
}

    public DatabaseReference getStudentReference()
    {
        return stRef;
    }


    public boolean addEnquiry(Enquiry e)
    {
        myRef.child(e.getEid()).child("eid").setValue(e.getEid());
        myRef.child(e.getEid()).child("name").setValue(e.getEqstname());
        myRef.child(e.getEid()).child("fname").setValue(e.getEqstfname());
        myRef.child(e.getEid()).child("class").setValue(e.getEqclasstojoin());
        myRef.child(e.getEid()).child("school").setValue(e.getEqschoolname());
        myRef.child(e.getEid()).child("board").setValue(e.getEqboard());
        myRef.child(e.getEid()).child("mobilno").setValue(e.getEqmobno());
        myRef.child(e.getEid()).child("whatsappno").setValue(e.getEqwhatsappno());
        myRef.child(e.getEid()).child("address").setValue(e.getEqaddress());
        myRef.child(e.getEid()).child("reference").setValue(e.getEqreferences());
        myRef.child(e.getEid()).child("remark").setValue(e.getEqremarks());
        myRef.child(e.getEid()).child("eqdate").setValue(e.getEqdate());

        return true;
    }
    public boolean deleteEnquiry(String id)
    {
        myRef.child(id).removeValue();
        return true;
    }

    public boolean addStudent(StudentInformation s)
    {
        stRef.child(s.getSid()).child("image").setValue(Arrays.toString(s.getStimage()));
        stRef.child(s.getSid()).child("sid").setValue(s.getSid());
        stRef.child(s.getSid()).child("name").setValue(s.getStudentName());
        stRef.child(s.getSid()).child("fathername").setValue(s.getStudentFatherName());
        stRef.child(s.getSid()).child("mothername").setValue(s.getStudentMotherName());
        stRef.child(s.getSid()).child("dob").setValue(s.getStudentDOB());
        stRef.child(s.getSid()).child("board").setValue(s.getStudentBoard());
        stRef.child(s.getSid()).child("class").setValue(s.getStudentClass());
        stRef.child(s.getSid()).child("course").setValue(s.getStudentCourse());
        stRef.child(s.getSid()).child("schoolname").setValue(s.getStudentSchoolname());
        stRef.child(s.getSid()).child("fqualification").setValue(s.getStudentFatherQualification());
        stRef.child(s.getSid()).child("foccupation").setValue(s.getStudentFatherOccupation());
        stRef.child(s.getSid()).child("mqualification").setValue(s.getStudentMotherQualification());
        stRef.child(s.getSid()).child("moccupation").setValue(s.getStudentMotherOccupation());
        stRef.child(s.getSid()).child("smobno").setValue(s.getStudentMobNo());
        stRef.child(s.getSid()).child("swtspno").setValue(s.getStudentWtspNo());
        stRef.child(s.getSid()).child("fmobno").setValue(s.getStudentfMobNo());
        stRef.child(s.getSid()).child("fwtspno").setValue(s.getStudentfWtspNo());
        stRef.child(s.getSid()).child("mmobno").setValue(s.getStudentmMobNo());
        stRef.child(s.getSid()).child("mwtspno").setValue(s.getStudentmWtspNo());
        stRef.child(s.getSid()).child("omobno").setValue(s.getStudentOtherMobNo());
        stRef.child(s.getSid()).child("localadd").setValue(s.getStudentLoacalAddress());
        stRef.child(s.getSid()).child("peradd").setValue(s.getStudentPermanentAddress());
        stRef.child(s.getSid()).child("ambition").setValue(s.getStudentAmbitions());
        stRef.child(s.getSid()).child("remark").setValue(s.getStudentRemark());
        stRef.child(s.getSid()).child("admisndate").setValue(s.getStudentAdmissionDate());

        return true;
    }

    public boolean deleteStudent(String id)
    {
        stRef.child(id).removeValue();
        return true;
    }

}
