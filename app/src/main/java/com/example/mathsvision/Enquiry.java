package com.example.mathsvision;

import java.io.Serializable;

public class Enquiry implements Serializable {
    public String eid,eqdate,eqstname,eqstfname,eqclasstojoin,eqschoolname,eqboard,eqmobno,eqwhatsappno,eqaddress,eqreferences,eqremarks;

    public Enquiry() {
    }

    public Enquiry(String eid, String eqdate, String eqstname, String eqstfname, String eqclasstojoin, String eqschoolname, String eqboard, String eqmobno, String eqwhatsappno, String eqaddress, String eqreferences, String eqremarks) {
        this.eid = eid;
        this.eqdate = eqdate;
        this.eqstname = eqstname;
        this.eqstfname = eqstfname;
        this.eqclasstojoin = eqclasstojoin;
        this.eqschoolname = eqschoolname;
        this.eqboard = eqboard;
        this.eqmobno = eqmobno;
        this.eqwhatsappno = eqwhatsappno;
        this.eqaddress = eqaddress;
        this.eqreferences = eqreferences;
        this.eqremarks = eqremarks;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEqdate() {
        return eqdate;
    }

    public void setEqdate(String eqdate) {
        this.eqdate = eqdate;
    }

    public String getEqstname() {
        return eqstname;
    }

    public void setEqstname(String eqstname) {
        this.eqstname = eqstname;
    }

    public String getEqstfname() {
        return eqstfname;
    }

    public void setEqstfname(String eqstfname) {
        this.eqstfname = eqstfname;
    }

    public String getEqclasstojoin() {
        return eqclasstojoin;
    }

    public void setEqclasstojoin(String eqclasstojoin) {
        this.eqclasstojoin = eqclasstojoin;
    }

    public String getEqschoolname() {
        return eqschoolname;
    }

    public void setEqschoolname(String eqschoolname) {
        this.eqschoolname = eqschoolname;
    }

    public String getEqboard() {
        return eqboard;
    }

    public void setEqboard(String eqboard) {
        this.eqboard = eqboard;
    }

    public String getEqmobno() {
        return eqmobno;
    }

    public void setEqmobno(String eqmobno) {
        this.eqmobno = eqmobno;
    }

    public String getEqwhatsappno() {
        return eqwhatsappno;
    }

    public void setEqwhatsappno(String eqwhatsappno) {
        this.eqwhatsappno = eqwhatsappno;
    }

    public String getEqaddress() {
        return eqaddress;
    }

    public void setEqaddress(String eqaddress) {
        this.eqaddress = eqaddress;
    }

    public String getEqreferences() {
        return eqreferences;
    }

    public void setEqreferences(String eqreferences) {
        this.eqreferences = eqreferences;
    }

    public String getEqremarks() {
        return eqremarks;
    }

    public void setEqremarks(String eqremarks) {
        this.eqremarks = eqremarks;
    }
}
