package com.lecturer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.model.RegistrationRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.PrefManager;
import com.lecturer.utils.Utils;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener,LectureAppContract.View {

    private LectureAppContract.Presenter mLectureAppPresenter;
    private EditText edtName,edtCourse,edtCourseCode,edtLevel,edtSemester,edtDepartment,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLectureAppPresenter = new LectureAppPresenter(this);

        findViewById(R.id.btn_register).setOnClickListener(this);

        edtName = findViewById(R.id.name);
        edtCourse = findViewById(R.id.course);
        edtCourseCode = findViewById(R.id.course_code);
        edtLevel = findViewById(R.id.level);
        edtSemester = findViewById(R.id.semester);
        edtDepartment = findViewById(R.id.department);
        edtPassword = findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                RegistrationRequest registrationRequest = new RegistrationRequest();
                registrationRequest.setLecturerName(edtName.getText().toString());
                registrationRequest.setCourse(edtCourse.getText().toString());
                registrationRequest.setCourseCode(edtCourseCode.getText().toString());
                registrationRequest.setLevel(edtLevel.getText().toString());
                registrationRequest.setSemester(edtSemester.getText().toString());
                registrationRequest.setDepartment(edtDepartment.getText().toString());
                registrationRequest.setPassword(edtPassword.getText().toString());
                registrationRequest.setUsername(edtName.getText().toString());
                if(Utils.isNetworkConnected(RegistrationActivity.this)) {
                    mLectureAppPresenter.registerUser(RegistrationActivity.this, registrationRequest);
                }else
                {
                    Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void setPresenter(LectureAppContract.Presenter presenter) {
        mLectureAppPresenter = presenter;
    }

    @Override
    public void onLoginSuccess(String message) {

    }

    @Override
    public void onLoginFailure(String message) {

    }

    @Override
    public void onRegistrationSuccess(String message) {

            new PrefManager(RegistrationActivity.this).setLoggedIn(true);
            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
    }

    @Override
    public void onRegistrationFailure(String message) {
        Toast.makeText(RegistrationActivity.this,"We could not register you at the moment. Please try again later.",Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void onStudentFailure(String message) {

    }
}
