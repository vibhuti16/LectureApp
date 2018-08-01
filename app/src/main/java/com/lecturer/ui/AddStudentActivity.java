package com.lecturer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lecturer.R;
import com.lecturer.model.InsertStudentRequest;
import com.lecturer.model.StudentListResponse;
import com.lecturer.model.ViewAttendanceResponse;
import com.lecturer.utils.PrefManager;
import com.lecturer.utils.Utils;


public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener,LectureAppContract.View{

    private EditText edtMatricNumber,edtStudentName;
    private LectureAppContract.Presenter mPresenter;
    private ProgressBar pgProgress;
    private RadioGroup rgGender;
    private String gender;
    private RadioButton rbMale,rbFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtMatricNumber = findViewById(R.id.edt_matric_number);
        edtStudentName = findViewById(R.id.edt_student_name);
        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(((RadioButton)findViewById(checkedId))!=null)
                gender = ((RadioButton)findViewById(checkedId)).getText().toString();
            }
        });

        mPresenter = new LectureAppPresenter(this);

        findViewById(R.id.btn_add_student).setOnClickListener(this);
        pgProgress = findViewById(R.id.progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_student:
                InsertStudentRequest insertStudentRequest = new InsertStudentRequest();
                insertStudentRequest.setStudentGender(gender);
                insertStudentRequest.setStudentMatricNo(edtMatricNumber.getText().toString());
                insertStudentRequest.setStudentName(edtStudentName.getText().toString());
                insertStudentRequest.setId(new PrefManager(AddStudentActivity.this).getString(PrefManager.LOGIN_ID));
                if(validateDetails())
                {
                    if(Utils.isNetworkConnected(AddStudentActivity.this)) {
                        pgProgress.setVisibility(View.VISIBLE);
                        mPresenter.addStudent(AddStudentActivity.this, insertStudentRequest);
                    }else
                    {
                        Toast.makeText(this,getResources().getString(R.string.network_connection_issue),Toast.LENGTH_LONG).show();
                    }
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
        pgProgress.setVisibility(View.GONE);
        Toast.makeText(AddStudentActivity.this,"Student added successfully!!",Toast.LENGTH_LONG).show();
        edtMatricNumber.setText("");
        edtStudentName.setText("");
        rgGender.clearCheck();
    }

    @Override
    public void onAddStudentFailure(String message) {
        pgProgress.setVisibility(View.GONE);
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

    private View focusView = null;
    private boolean validateDetails(){
        if(edtMatricNumber.getText()==null || TextUtils.isEmpty(edtMatricNumber.getText().toString())){
            focusView = edtMatricNumber;
        }else if(edtStudentName.getText()==null || TextUtils.isEmpty(edtStudentName.getText().toString())){
            focusView = edtStudentName;
        }else if(!rbMale.isChecked()&&!rbFemale.isChecked()){
            Toast.makeText(AddStudentActivity.this,"Please enter a gender",Toast.LENGTH_LONG).show();
            return false;
        }


        if(focusView!=null){
            focusView.requestFocus();
            ((EditText)focusView).setError(getString(R.string.error_field_required));
            focusView = null;
            return false;
        }else
        {
            return true;
        }

    }
}
