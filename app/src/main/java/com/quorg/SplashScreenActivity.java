package com.quorg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.quorg.utility.Pref;
import com.quorg.utility.Preference;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (Pref.getIsLogin()) {

                    if(Pref.setIsEdit()){
                        Intent i = new Intent(SplashScreenActivity.this, DashBoard.class);
                        startActivity(i);
                        finish();
                    }else
                    {
                        Intent i = new Intent(SplashScreenActivity.this, ProfileScreen.class);
                        startActivity(i);
                        finish();
                    }

                } else {
                    Intent i = new Intent(SplashScreenActivity.this, Loginscreen.class);
                    startActivity(i);
                    finish();
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
