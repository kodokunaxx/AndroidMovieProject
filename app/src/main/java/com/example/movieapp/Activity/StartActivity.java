package com.example.movieapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.movieapp.R;

public class StartActivity extends Activity {
    Button btnSignInStart,btnSignUpStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start);

        setSignIn();
        setSignUp();
    }

    private void setSignUp() {
        btnSignUpStart = findViewById(R.id.btnSignUpStart);
        btnSignUpStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setSignIn(){
        btnSignInStart = findViewById(R.id.btnSignInStart);
        btnSignInStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}