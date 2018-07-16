package com.lecturer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.model.LoginRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.Utils;


public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener,LectureAppContract.View {

    private EditText edtUsername,edtPassword;
    private LectureAppContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);

        presenter = new LectureAppPresenter(ForgotPasswordActivity.this);

        findViewById(R.id.btn_forgot_password).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_forgot_password:

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUserName(edtUsername.getText().toString());
                loginRequest.setPassword(edtPassword.getText().toString());
                if(Utils.isNetworkConnected(ForgotPasswordActivity.this)) {
                    presenter.forgotPassword(ForgotPasswordActivity.this, loginRequest);
                }else
                {
                    Toast.makeText(ForgotPasswordActivity.this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    @Override
    public void setPresenter(LectureAppContract.Presenter presenter) {

        this.presenter = presenter;
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

        Toast.makeText(ForgotPasswordActivity.this,"Password successfully set!!",Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onForgotPasswordFailure(String message) {
        Toast.makeText(ForgotPasswordActivity.this,"Your password could not be set at the moment. Please try again later!!",Toast.LENGTH_LONG).show();
        finish();
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
