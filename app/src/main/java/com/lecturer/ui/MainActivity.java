package com.lecturer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.lecturer.R;
import com.lecturer.utils.PrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cv_add_student).setOnClickListener(this);
        findViewById(R.id.cv_take_attendance).setOnClickListener(this);
        findViewById(R.id.cv_view_attendance).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_add_student:
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_take_attendance:
                intent = new Intent(MainActivity.this,TakeAttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.cv_view_attendance:
                intent = new Intent(MainActivity.this,ViewAttendanceActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                new PrefManager(MainActivity.this).setLoggedIn(false);
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
        }
        return true;
    }
}
