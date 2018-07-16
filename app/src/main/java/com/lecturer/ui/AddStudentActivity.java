package com.lecturer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.model.InsertStudentRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.Utils;


public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener,LectureAppContract.View{

    private EditText edtMatricNumber,edtStudentName,edtGender;
    private LectureAppContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtMatricNumber = findViewById(R.id.edt_matric_number);
        edtStudentName = findViewById(R.id.edt_student_name);
        edtGender = findViewById(R.id.edt_gender);

        mPresenter = new LectureAppPresenter(this);

        findViewById(R.id.btn_add_student).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_student:
                InsertStudentRequest insertStudentRequest = new InsertStudentRequest();
                insertStudentRequest.setStudentGender(edtGender.getText().toString());
                insertStudentRequest.setStudentMatricNo(edtMatricNumber.getText().toString());
                insertStudentRequest.setStudentName(edtStudentName.getText().toString());
                if(Utils.isNetworkConnected(AddStudentActivity.this)) {
                    mPresenter.addStudent(AddStudentActivity.this, insertStudentRequest);
                }else
                {
                    Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
                }

        }
    }

    @Override
    public void setPresenter(LectureAppContract.Presenter presenter) {
        mPresenter = presenter;
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

        Toast.makeText(AddStudentActivity.this,"Student added successfully!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddStudentFailure(String message) {

        Toast.makeText(AddStudentActivity.this,"Student could not be added at the moment. Please try again later",Toast.LENGTH_LONG).show();
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
