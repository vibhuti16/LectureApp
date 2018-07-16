package com.lecturer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vibhuti on 7/16/2018.
 */

public class Student implements Parcelable{

    private String Student_id;
    private String Student_matric_no;

    public String getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(String student_id) {
        Student_id = student_id;
    }

    public String getStudent_matric_no() {
        return Student_matric_no;
    }

    public void setStudent_matric_no(String student_matric_no) {
        Student_matric_no = student_matric_no;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }

    public String getStudent_gender() {
        return Student_gender;
    }

    public void setStudent_gender(String student_gender) {
        Student_gender = student_gender;
    }

    private String Student_name;
    private String Student_gender;

    private int present;

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    private int absent;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Student_id);
        dest.writeString(Student_matric_no);
        dest.writeString(Student_name);
        dest.writeString(Student_gender);
        dest.writeInt(present);
        dest.writeInt(absent);
    }

    private Student(Parcel in){

        Student_id = in.readString();
        Student_matric_no = in.readString();
        Student_name = in.readString();
        Student_gender = in.readString();
        present = in.readInt();
        absent = in.readInt();

    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };


}
