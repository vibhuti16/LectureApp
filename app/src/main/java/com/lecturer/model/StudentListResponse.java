package com.lecturer.model;

import java.util.ArrayList;

/**
 * Created by Vibhuti on 7/15/2018.
 */

public class StudentListResponse {

    public ArrayList<Student> getStudent() {
        return Student;
    }

    public void setStudent(ArrayList<Student> student) {
        Student = student;
    }

    private ArrayList<Student> Student;
}
