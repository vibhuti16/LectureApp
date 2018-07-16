package com.lecturer.model;

import java.util.ArrayList;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class TakeAttendanceRequest {

    public ArrayList<TakeAttendence> getTakeAttendanceRequestArrayList() {
        return takeAttendanceRequestArrayList;
    }

    public void setTakeAttendanceRequestArrayList(ArrayList<TakeAttendence> takeAttendanceRequestArrayList) {
        this.takeAttendanceRequestArrayList = takeAttendanceRequestArrayList;
    }

    private ArrayList<TakeAttendence> takeAttendanceRequestArrayList;
}
