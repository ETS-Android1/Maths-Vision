package com.example.mathsvision;

import com.google.firebase.database.DatabaseReference;

public class FirebaseHelper {

    public boolean addEnquiry(Enquiry e) {
    return true;
    }

    public void deleteEnquiry(String s) {
    }

    public boolean addStudent(StudentInformation s) {
        boolean check=true;
        return check;
    }

    public DatabaseReference getEnquiryReference() {
        DatabaseReference e=null;
        return e;

    }
}
