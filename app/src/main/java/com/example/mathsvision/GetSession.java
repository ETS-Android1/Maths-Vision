package com.example.mathsvision;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetSession {
    public String getSession()
    {
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String datear[]=date.split("-");
        String year = datear[2];
        char [] sep = year.toCharArray();
        int f = Integer.parseInt(String.valueOf(sep[2]));
        int s = Integer.parseInt(String.valueOf(sep[3]));
        int incr = (f*10)+s+1;
        String se = Integer.toString(incr);
        return year+"-"+se;
    }
}
