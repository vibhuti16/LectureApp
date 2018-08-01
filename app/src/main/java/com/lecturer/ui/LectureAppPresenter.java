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
import com.lecturer.model.TakeAttendanceResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.PrefManager;

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
                mView.onLoginSuccess("");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mView.onLoginFailure("");

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

                            mView.onRegistrationSuccess("");


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
                mView.onForgotPasswordSuccess("");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mView.onForgotPasswordFailure("");
            }
        });
    }

    @Override
    public void addStudent(Activity context, InsertStudentRequest insertStudentRequest) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.insertStudent(insertStudentRequest.getStudentMatricNo(),insertStudentRequest.getStudentName(),insertStudentRequest.getStudentGender(),insertStudentRequest.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mView.onAddStudentSuccess("");
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
        lectureDataService.viewAttendance((new PrefManager(context).getString(PrefManager.LOGIN_ID))).enqueue(new Callback<ViewAttendanceResponse>() {
            @Override
            public void onResponse(Call<ViewAttendanceResponse> call, Response<ViewAttendanceResponse> response) {
                mView.onViewAttendanceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ViewAttendanceResponse> call, Throwable t) {
                mView.onTakeAttendanceFailure("");
            }
        });
    }


    @Override
    public void getStudentList(Activity context, final boolean isAfterAttendanceTaken) {
        LectureDataService lectureDataService = ServiceFactory.createRetrofitService(LectureDataService.class,LectureDataService.SERVICE_ENDPOINT);
        lectureDataService.getStudentList((new PrefManager(context).getString(PrefManager.LOGIN_ID))).enqueue(new Callback<StudentListResponse>() {
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
//        String json = new Gson().toJson(takeAttendanceRequest.getTakeAttendanceRequestArrayList());
//        Log.d("kjbjk",json);
        lectureDataService.insertAttendance(takeAttendanceRequest.getTakeAttendanceRequestArrayList()).enqueue(new Callback<TakeAttendanceResponse>() {
            @Override
            public void onResponse(Call<TakeAttendanceResponse> call, Response<TakeAttendanceResponse> response) {
                if(response.body().getMessage().equalsIgnoreCase("successful")){
                    mView.onTakeAttendanceSuccess("");
                }else
                {
                    mView.onTakeAttendanceFailure("");
                }

            }

            @Override
            public void onFailure(Call<TakeAttendanceResponse> call, Throwable t) {
                mView.onTakeAttendanceFailure("");

            }
        });
    }
}
