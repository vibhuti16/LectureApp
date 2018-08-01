package com.lecturer.apis;

import com.lecturer.model.StudentListResponse;
import com.lecturer.model.TakeAttendanceResponse;
import com.lecturer.model.TakeAttendence;
import com.lecturer.model.ViewAttendanceResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Vibhuti on 5/14/2018.
 */

public interface LectureDataService {

    String SERVICE_ENDPOINT = "http://srengvadodara.com";

    @POST("/api/Insert_lecturer")
    @FormUrlEncoded
    Call<String> registerLecturer(@Field("lecturerName") String lecturerName,
                                  @Field("course") String course,
                                  @Field("courseCode") String courseCode,
                                  @Field("level") String level,
                                  @Field("semester") String semester,
                                  @Field("department") String department,
                                  @Field("username") String username,
                                  @Field("password") String password);

    @POST("/api/Lecturer_login")
    @FormUrlEncoded
    Call<String> login(@Field("userName") String userName,
                       @Field("password") String password);

    @POST("/api/forgot_password")
    @FormUrlEncoded
    Call<String> forgotPassword(@Field("userName") String userName,
                       @Field("password") String password);

    @POST("/api/Insert_student")
    @FormUrlEncoded
    Call<String> insertStudent(@Field("studentMatricNo") String studentMatricNo,
                                @Field("studentName") String studentName,
                               @Field("studentGender") String studentGender,
                               @Field("id") String id);

    @POST("/api/Student")
    @FormUrlEncoded
    Call<StudentListResponse> getStudentList(@Field("id") String id);

    @POST("/api/Total_attendance")
    @FormUrlEncoded
    Call<ViewAttendanceResponse> viewAttendance(@Field("id") String id);

    @POST("/api/Update_attendance")
    Call<TakeAttendanceResponse> insertAttendance(@Body ArrayList<TakeAttendence> takeAttendences);
}
