package com.lecturer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.adapter.TakeAttendanceAdapter;
import com.lecturer.model.Student;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.TakeAttendanceRequest;
import com.lecturer.model.TakeAttendence;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.PrefManager;
import com.lecturer.utils.Utils;

import java.util.ArrayList;

public class TakeAttendanceActivity extends AppCompatActivity implements LectureAppContract.View, View.OnClickListener{

    private LectureAppContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TakeAttendanceAdapter takeAttendanceAdapter;
    private ProgressBar pgProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.rv_take_attendance);
        mRecyclerView.setLayoutManager(layoutManager);
        pgProgress = findViewById(R.id.progress);
        mPresenter = new LectureAppPresenter(TakeAttendanceActivity.this);
        if(Utils.isNetworkConnected(TakeAttendanceActivity.this)) {
            pgProgress.setVisibility(View.VISIBLE);
            mPresenter.getStudentList(this,false);
        }else
        {
            Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.btn_take_attendance).setOnClickListener(this);

    }

    @Override
    public void setPresenter(LectureAppContract.Presenter presenter) {
        this.mPresenter = presenter;
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
        pgProgress.setVisibility(View.GONE);
        if(Utils.isNetworkConnected(TakeAttendanceActivity.this)) {
            mPresenter.getStudentList(this,true);
        }else
        {
            Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onTakeAttendanceFailure(String message) {
        pgProgress.setVisibility(View.GONE);
        Toast.makeText(TakeAttendanceActivity.this,"We could not update attendance at the moment. Please try again later.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewAttendanceSuccess(ViewAttendanceResponse viewAttendanceResponse) {

    }

    @Override
    public void onViewAttendanceFailure(String message) {

    }

    @Override
    public void onStudentSuccess(StudentListResponse studentListResponse, boolean isAfterAttendanceTaken) {
        pgProgress.setVisibility(View.GONE);
        if(isAfterAttendanceTaken){
            Intent intent = new Intent(TakeAttendanceActivity.this,ViewAttendanceActivity.class);
            intent.putParcelableArrayListExtra("student_list",studentListResponse.getStudent());
            startActivity(intent);
        }else
        {
            if(studentListResponse!=null && studentListResponse.getStudent()!=null){
                takeAttendanceAdapter = new TakeAttendanceAdapter(this,studentListResponse);
                mRecyclerView.setAdapter(takeAttendanceAdapter);
            }

        }

    }

    @Override
    public void onStudentFailure(String message) {
        pgProgress.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_take_attendance:

                StudentListResponse studentListResponse = takeAttendanceAdapter.getStudentListResponses();

                TakeAttendanceRequest takeAttendanceRequest = new TakeAttendanceRequest();
                ArrayList<TakeAttendence> takeAttendanceList = new ArrayList<>();
                String lectureId = new PrefManager(TakeAttendanceActivity.this).getString(PrefManager.LOGIN_ID);
                for(Student student : studentListResponse.getStudent()){
                    TakeAttendence takeAttendence = new TakeAttendence();
                    takeAttendence.setName(student.getStudent_name());
                    takeAttendence.setId(student.getStudent_id());
                    takeAttendence.setPresent(student.getPresent()+"");
                    takeAttendence.setAbsent(student.getAbsent()+"");
                    takeAttendence.setLecture_id(lectureId);
                    takeAttendanceList.add(takeAttendence);
                }
                takeAttendanceRequest.setTakeAttendanceRequestArrayList(takeAttendanceList);
                takeAttendanceRequest.setTakeAttendanceRequestArrayList(takeAttendanceList);
                if(Utils.isNetworkConnected(TakeAttendanceActivity.this)) {
                    pgProgress.setVisibility(View.VISIBLE);
                    mPresenter.takeAttendance(this, takeAttendanceRequest);
                }else
                {
                    Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
