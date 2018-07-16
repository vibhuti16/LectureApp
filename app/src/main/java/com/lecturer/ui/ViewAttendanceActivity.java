package com.lecturer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.adapter.ViewAttendanceAdapter;
import com.lecturer.model.Student;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.Utils;

import java.util.ArrayList;

public class ViewAttendanceActivity extends AppCompatActivity implements LectureAppContract.View {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LectureAppContract.Presenter lectureAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.rv_view_attendance);
        mRecyclerView.setLayoutManager(layoutManager);
        lectureAppPresenter = new LectureAppPresenter(this);

        if(getIntent().hasExtra("student_list")){
            ArrayList<Student> studentArrayList = getIntent().getParcelableArrayListExtra("student_list");
            ViewAttendanceAdapter viewAttendanceAdapter = new ViewAttendanceAdapter(studentArrayList);
            mRecyclerView.setAdapter(viewAttendanceAdapter);
        }else
        {
            if(Utils.isNetworkConnected(this)){
                lectureAppPresenter.getStudentList(this,false);
            }else
            {
                Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void setPresenter(LectureAppContract.Presenter presenter) {
        lectureAppPresenter = presenter;
    }

    @Override
    public void onLoginSuccess(String message) {

    }

    @Override
    public void onLoginFailure(String message) {

    }

    @Override
    public void onRegistrationSuccess(String message) {

    }

    @Override
    public void onRegistrationFailure(String message) {

    }

    @Override
    public void onForgotPasswordSuccess(String message) {

    }

    @Override
    public void onForgotPasswordFailure(String message) {

    }

    @Override
    public void onAddStudentSuccess(String message) {

    }

    @Override
    public void onAddStudentFailure(String message) {

    }

    @Override
    public void onTakeAttendanceSuccess(String message) {

    }

    @Override
    public void onTakeAttendanceFailure(String message) {

    }

    @Override
    public void onViewAttendanceSuccess(ViewAttendanceResponse viewAttendanceResponse) {

    }

    @Override
    public void onViewAttendanceFailure(String message) {

    }

    @Override
    public void onStudentSuccess(StudentListResponse studentListResponse, boolean isAfterAttendanceTaken) {
        ArrayList<Student> studentArrayList = studentListResponse.getStudent();
        ViewAttendanceAdapter viewAttendanceAdapter = new ViewAttendanceAdapter(studentArrayList);
        mRecyclerView.setAdapter(viewAttendanceAdapter);
    }

    @Override
    public void onStudentFailure(String message) {

    }
}
