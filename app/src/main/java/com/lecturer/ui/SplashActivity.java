package com.lecturer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.lecturer.R;
import com.lecturer.utils.PrefManager;

public class SplashActivity extends AppCompatActivity {

    private  static int splash_delay_time=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent intent;
                if(new PrefManager(SplashActivity.this).isLoggedIn()){
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                }else{
                    intent=new Intent(SplashActivity.this,LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },splash_delay_time);
    }

}
