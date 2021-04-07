package com.example.movieapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.movieapp.R;

public class StartActivity extends Activity {
    Button signInBt,signUpBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start);

        setSignIn();
        setSignUp();
    }

    private void setSignUp() {
        signUpBt = findViewById(R.id.btnSignUp);
        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setSignIn(){
        signInBt = findViewById(R.id.btnSignIn);
        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}