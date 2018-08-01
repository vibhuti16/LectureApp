package com.lecturer.model;

import java.util.ArrayList;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class TakeAttendanceRequest {

    public ArrayList<TakeAttendence> getTakeAttendanceRequestArrayList() {
        return TakeAttendence;
    }

    public void setTakeAttendanceRequestArrayList(ArrayList<TakeAttendence> takeAttendanceRequestArrayList) {
        this.TakeAttendence = takeAttendanceRequestArrayList;
    }

    private ArrayList<TakeAttendence> TakeAttendence;
}
