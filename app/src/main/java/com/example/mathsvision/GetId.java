package com.example.mathsvision;

import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetId {
    public String getEnquiryId(String e)
    {

        String date,time;

        date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        time=new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        String sepdate[]=date.split("-");
        String septime[]=time.split(":");
        char y[]=sepdate[2].toCharArray();
        String f=String.valueOf(y[2]);
        String s=String.valueOf(y[3]);
        if (e.matches("E"))
        return "MV-"+septime[1]+sepdate[0]+septime[2]+septime[0]+sepdate[1]+f+s+"-E";
        else if (e.matches("S"))
            return "MV-"+septime[1]+sepdate[0]+septime[2]+septime[0]+sepdate[1]+f+s+"-S";
        else
            return "MV-"+septime[1]+sepdate[0]+septime[2]+septime[0]+sepdate[1]+f+s+"-T";
    }
}
