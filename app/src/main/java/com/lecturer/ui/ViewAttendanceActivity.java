package com.lecturer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.adapter.ViewAttendanceAdapter;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.Utils;

public class ViewAttendanceActivity extends AppCompatActivity implements LectureAppContract.View {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LectureAppContract.Presenter lectureAppPresenter;
    private ProgressBar pgProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.rv_view_attendance);
        mRecyclerView.setLayoutManager(layoutManager);
        pgProgress = findViewById(R.id.progress);
        lectureAppPresenter = new LectureAppPresenter(this);


            if(Utils.isNetworkConnected(this)){
                pgProgress.setVisibility(View.VISIBLE);
                lectureAppPresenter.viewAttendance(this);
            }else
            {
                Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
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
        pgProgress.setVisibility(View.GONE);
        ViewAttendanceAdapter viewAttendanceAdapter = new ViewAttendanceAdapter(this,viewAttendanceResponse);
        mRecyclerView.setAdapter(viewAttendanceAdapter);
    }

    @Override
    public void onViewAttendanceFailure(String message) {
        pgProgress.setVisibility(View.GONE);
    }

    @Override
    public void onStudentSuccess(StudentListResponse studentListResponse, boolean isAfterAttendanceTaken) {

    }

    @Override
    public void onStudentFailure(String message) {

    }
}
