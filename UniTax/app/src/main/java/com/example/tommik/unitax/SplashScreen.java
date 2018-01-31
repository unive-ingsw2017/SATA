package com.example.tommik.unitax;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Handler handler;
        final int TIME_OUT = 1500;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, SelectUniActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
