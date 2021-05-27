package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.movieapp.R;

public class SplashScreenActivity extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USERNAME = "username";
    SharedPreferences sharedpreferences;
    Handler handler;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                name = sharedpreferences.getString(USERNAME, null);
                Intent intent;
                if(name != null){
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(SplashScreenActivity.this, StartActivity.class);
                    startActivity(intent);
                }
            }
        },3000);
    }
}