package com.example.mathsvision;


import java.io.Serializable;

public class StudentInformation implements Serializable {

    private byte[] stimage;
    private String sid,studentName,studentFatherName,studentMotherName,studentDOB,studentBoard,studentClass,studentCourse,studentSchoolname
            ,studentFatherQualification,studentMotherQualification,studentFatherOccupation,studentMotherOccupation,studentMobNo,studentWtspNo
            ,studentfMobNo,studentfWtspNo,studentmMobNo,studentmWtspNo,studentOtherMobNo,studentLoacalAddress,studentPermanentAddress
            ,studentAmbitions,studentRemark,studentAdmissionDate;

    public byte[] getStimage() {
        return stimage;
    }

    public String getStudentAdmissionDate() {
        return studentAdmissionDate;
    }

    public void setStudentAdmissionDate(String studentAdmissionDate) {
        this.studentAdmissionDate = studentAdmissionDate;
    }

    public StudentInformation() {

    }

    public StudentInformation(byte[] stimage, String sid, String studentName, String studentFatherName, String studentMotherName, String studentDOB, String studentBoard, String studentClass, String studentCourse, String studentSchoolname, String studentFatherQualification, String studentMotherQualification, String studentFatherOccupation, String studentMotherOccupation, String studentMobNo, String studentWtspNo, String studentfMobNo, String studentfWtspNo, String studentmMobNo, String studentmWtspNo, String studentOtherMobNo, String studentLoacalAddress, String studentPermanentAddress, String studentAmbitions, String studentRemark) {
        this.stimage = stimage;
        this.sid = sid;
        this.studentName = studentName;
        this.studentFatherName = studentFatherName;
        this.studentMotherName = studentMotherName;
        this.studentDOB = studentDOB;
        this.studentBoard = studentBoard;
        this.studentClass = studentClass;
        this.studentCourse = studentCourse;
        this.studentSchoolname = studentSchoolname;
        this.studentFatherQualification = studentFatherQualification;
        this.studentMotherQualification = studentMotherQualification;
        this.studentFatherOccupation = studentFatherOccupation;
        this.studentMotherOccupation = studentMotherOccupation;
        this.studentMobNo = studentMobNo;
        this.studentWtspNo = studentWtspNo;
        this.studentfMobNo = studentfMobNo;
        this.studentfWtspNo = studentfWtspNo;
        this.studentmMobNo = studentmMobNo;
        this.studentmWtspNo = studentmWtspNo;
        this.studentOtherMobNo = studentOtherMobNo;
        this.studentLoacalAddress = studentLoacalAddress;
        this.studentPermanentAddress = studentPermanentAddress;
        this.studentAmbitions = studentAmbitions;
        this.studentRemark = studentRemark;
    }

    public void setStimage(byte[] stimage) {
        this.stimage = stimage;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public String getStudentMotherName() {
        return studentMotherName;
    }

    public void setStudentMotherName(String studentMotherName) {
        this.studentMotherName = studentMotherName;
    }

    public String getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        this.studentDOB = studentDOB;
    }

    public String getStudentBoard() {
        return studentBoard;
    }

    public void setStudentBoard(String studentBoard) {
        this.studentBoard = studentBoard;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentSchoolname() {
        return studentSchoolname;
    }

    public void setStudentSchoolname(String studentSchoolname) {
        this.studentSchoolname = studentSchoolname;
    }

    public String getStudentFatherQualification() {
        return studentFatherQualification;
    }

    public void setStudentFatherQualification(String studentFatherQualification) {
        this.studentFatherQualification = studentFatherQualification;
    }

    public String getStudentMotherQualification() {
        return studentMotherQualification;
    }

    public void setStudentMotherQualification(String studentMotherQualification) {
        this.studentMotherQualification = studentMotherQualification;
    }

    public String getStudentFatherOccupation() {
        return studentFatherOccupation;
    }

    public void setStudentFatherOccupation(String studentFatherOccupation) {
        this.studentFatherOccupation = studentFatherOccupation;
    }

    public String getStudentMotherOccupation() {
        return studentMotherOccupation;
    }

    public void setStudentMotherOccupation(String studentMotherOccupation) {
        this.studentMotherOccupation = studentMotherOccupation;
    }

    public String getStudentMobNo() {
        return studentMobNo;
    }

    public void setStudentMobNo(String studentMobNo) {
        this.studentMobNo = studentMobNo;
    }

    public String getStudentWtspNo() {
        return studentWtspNo;
    }

    public void setStudentWtspNo(String studentWtspNo) {
        this.studentWtspNo = studentWtspNo;
    }

    public String getStudentfMobNo() {
        return studentfMobNo;
    }

    public void setStudentfMobNo(String studentfMobNo) {
        this.studentfMobNo = studentfMobNo;
    }

    public String getStudentfWtspNo() {
        return studentfWtspNo;
    }

    public void setStudentfWtspNo(String studentfWtspNo) {
        this.studentfWtspNo = studentfWtspNo;
    }

    public String getStudentmMobNo() {
        return studentmMobNo;
    }

    public void setStudentmMobNo(String studentmMobNo) {
        this.studentmMobNo = studentmMobNo;
    }

    public String getStudentmWtspNo() {
        return studentmWtspNo;
    }

    public void setStudentmWtspNo(String studentmWtspNo) {
        this.studentmWtspNo = studentmWtspNo;
    }

    public String getStudentOtherMobNo() {
        return studentOtherMobNo;
    }

    public void setStudentOtherMobNo(String studentOtherMobNo) {
        this.studentOtherMobNo = studentOtherMobNo;
    }

    public String getStudentLoacalAddress() {
        return studentLoacalAddress;
    }

    public void setStudentLoacalAddress(String studentLoacalAddress) {
        this.studentLoacalAddress = studentLoacalAddress;
    }

    public String getStudentPermanentAddress() {
        return studentPermanentAddress;
    }

    public void setStudentPermanentAddress(String studentPermanentAddress) {
        this.studentPermanentAddress = studentPermanentAddress;
    }

    public String getStudentAmbitions() {
        return studentAmbitions;
    }

    public void setStudentAmbitions(String studentAmbitions) {
        this.studentAmbitions = studentAmbitions;
    }

    public String getStudentRemark() {
        return studentRemark;
    }

    public void setStudentRemark(String studentRemark) {
        this.studentRemark = studentRemark;
    }
}
