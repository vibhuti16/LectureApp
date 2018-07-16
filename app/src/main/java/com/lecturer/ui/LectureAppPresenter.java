package com.lecturer.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lecturer.apis.LectureDataService;
import com.lecturer.apis.ServiceFactory;
import com.lecturer.model.InsertStudentRequest;
import com.lecturer.model.LoginRequest;
import com.lecturer.model.RegistrationRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.TakeAttendanceRequest;
import com.lecturer.model.ViewAttendanceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vibhuti on 7/13/2018.
 */

public class LectureAppPresenter implements LectureAppContract.Presenter {

    private LectureAppContract.View mView;

    public LectureAppPresenter(LectureAppContract.View view){
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void start(@Nullable Bundle extras) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void login(Activity context, LoginRequest loginRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.login(loginRequest.getUserName(),loginRequest.getPassword()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mView.onLoginSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mView.onLoginSuccess("Success");

            }
        });
    }

    @Override
    public void registerUser(Activity context, RegistrationRequest registrationRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.registerLecturer(registrationRequest.getLecturerName(),registrationRequest.getCourse(),registrationRequest.getCourseCode(),
               registrationRequest.getLevel(),registrationRequest.getSemester(),registrationRequest.getDepartment(),registrationRequest.getUsername(),registrationRequest.getPassword()).
                enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int code = response.code();
                        if(code == 200){
                            mView.onRegistrationSuccess("");
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mView.onRegistrationFailure("");
                    }
                });
    }

    @Override
    public void forgotPassword(Activity context, LoginRequest loginRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.forgotPassword(loginRequest.getUserName(),loginRequest.getPassword()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mView.onForgotPasswordSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mView.onForgotPasswordSuccess("");
            }
        });
    }

    @Override
    public void addStudent(Activity context, InsertStudentRequest insertStudentRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.insertStudent(insertStudentRequest.getStudentMatricNo(),insertStudentRequest.getStudentName(),insertStudentRequest.getStudentGender()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mView.onAddStudentSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mView.onAddStudentFailure("");

            }
        });
    }



    @Override
    public void viewAttendance(Activity context) {

        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.viewAttendance().enqueue(new Callback<ViewAttendanceResponse>() {
            @Override
            public void onResponse(Call<ViewAttendanceResponse> call, Response<ViewAttendanceResponse> response) {
                mView.onViewAttendanceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ViewAttendanceResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void getStudentList(Activity context, final boolean isAfterAttendanceTaken) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.getStudentList().enqueue(new Callback<StudentListResponse>() {
            @Override
            public void onResponse(Call<StudentListResponse> call, Response<StudentListResponse> response) {
                mView.onStudentSuccess(response.body(),isAfterAttendanceTaken);
            }

            @Override
            public void onFailure(Call<StudentListResponse> call, Throwable t) {
                mView.onAddStudentFailure("");

            }
        });
    }

    @Override
    public void takeAttendance(Activity context, TakeAttendanceRequest takeAttendanceRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.insertAttendance(takeAttendanceRequest.getTakeAttendanceRequestArrayList()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                mView.onTakeAttendanceSuccess("");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mView.onTakeAttendanceFailure("");

            }
        });
    }
}
