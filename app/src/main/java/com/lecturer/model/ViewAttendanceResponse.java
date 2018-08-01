package com.lecturer.model;

import java.util.ArrayList;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class ViewAttendanceResponse {

    public ArrayList<Total_Attendance> getViewAttendances() {
        return Total_Attendance;
    }

    public void setViewAttendances(ArrayList<Total_Attendance> viewAttendances) {
        this.Total_Attendance = viewAttendances;
    }

    private ArrayList<Total_Attendance> Total_Attendance;



    public class Total_Attendance{
        private String Student_name;
        private String Total_present;
        private String Student_id;

        public String getStudent_name() {
            return Student_name;
        }

        public void setStudent_name(String student_name) {
            Student_name = student_name;
        }

        public String getTotal_present() {
            return Total_present;
        }

        public void setTotal_present(String total_present) {
            Total_present = total_present;
        }

        public String getStudent_id() {
            return Student_id;
        }

        public void setStudent_id(String student_id) {
            Student_id = student_id;
        }

        public String getTotal_absent() {
            return Total_absent;
        }

        public void setTotal_absent(String total_absent) {
            Total_absent = total_absent;
        }

        private String Total_absent;
    }
}
