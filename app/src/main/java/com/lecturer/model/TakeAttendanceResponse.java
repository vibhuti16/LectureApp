package com.lecturer.model;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class TakeAttendanceResponse {

   private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
