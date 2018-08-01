package com.lecturer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lecturer.R;
import com.lecturer.utils.PrefManager;

public class SplashActivity extends AppCompatActivity {

    private  static int splash_delay_time=4000;
    private TextView txtSplash;
    private static final String SPLASH_TEXT_INDEX= "splash_text_index";
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

        txtSplash = findViewById(R.id.txt_splash);
        int i = new PrefManager(this).getInt(SPLASH_TEXT_INDEX);
        txtSplash.setText(getResources().getStringArray(R.array.educational_quotes)[i]);
        i = i+1;
        if(i==getResources().getStringArray(R.array.educational_quotes).length){
            i=0;
        }
        new PrefManager(this).setInt(SPLASH_TEXT_INDEX,i);
    }

}
