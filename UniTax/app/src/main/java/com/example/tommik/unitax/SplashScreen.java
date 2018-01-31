package com.example.tommik.unitax;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int TIME_OUT = 1500;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {



                    //  Launch app intro
                    //final Intent i = new Intent(SplashScreen.this, IntroActivity.class);

                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            //startActivity(i);
                            new Handler().postDelayed(new Runnable() {

                                @Override

                                public void run() {
                                    Intent i = new Intent(SplashScreen.this, IntroActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                            }, TIME_OUT);
                        }
                    });

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                Intent i = new Intent(SplashScreen.this, SelectUniActivity.class);
                startActivity(i);
                finish();
            }

        }, TIME_OUT);

    }
}


/*public class SplashScreen extends AppCompatActivity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        final int TIME_OUT = 4000;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                Intent i = new Intent(SplashScreen.this, SelectUniActivity.class);

                startActivity(i);

                finish();

            }

        }, TIME_OUT);

    }

}
*/