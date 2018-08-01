package com.lecturer.model;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class InsertStudentRequest {

    public String getStudentMatricNo() {
        return studentMatricNo;
    }

    public void setStudentMatricNo(String studentMatricNo) {
        this.studentMatricNo = studentMatricNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    private String studentMatricNo;
    private String studentName;
    private String studentGender;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
