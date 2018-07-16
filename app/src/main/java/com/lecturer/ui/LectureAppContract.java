package com.lecturer.ui;

import android.app.Activity;

import com.lecturer.BasePresenter;
import com.lecturer.BaseView;
import com.lecturer.model.InsertStudentRequest;
import com.lecturer.model.LoginRequest;
import com.lecturer.model.RegistrationRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.TakeAttendanceRequest;
import com.lecturer.model.ViewAttendanceResponse;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class LectureAppContract {

    interface View extends BaseView<Presenter>{

        public void onLoginSuccess(String message);
        public void onLoginFailure(String message);
        public void onRegistrationSuccess(String message);
        public void onRegistrationFailure(String message);
        public void onForgotPasswordSuccess(String message);
        public void onForgotPasswordFailure(String message);
        public void onAddStudentSuccess(String message);
        public void onAddStudentFailure(String message);
        public void onTakeAttendanceSuccess(String message);
        public void onTakeAttendanceFailure(String message);
        public void onViewAttendanceSuccess(ViewAttendanceResponse viewAttendanceResponse);
        public void onViewAttendanceFailure(String message);
        public void onStudentSuccess(StudentListResponse studentListResponse,boolean isAfterAttendanceTaken);
        public void onStudentFailure(String message);
    }

    interface Presenter extends BasePresenter{

        void login(Activity context, LoginRequest loginRequest);
        void forgotPassword(Activity context, LoginRequest loginRequest);
        void registerUser(Activity context, RegistrationRequest registrationRequest);
        void addStudent(Activity context, InsertStudentRequest insertStudentRequest);
        void getStudentList(Activity context,boolean isAfterAttendanceTaken);
        void takeAttendance(Activity context, TakeAttendanceRequest takeAttendanceRequest);
        void viewAttendance(Activity context);
    }
}
