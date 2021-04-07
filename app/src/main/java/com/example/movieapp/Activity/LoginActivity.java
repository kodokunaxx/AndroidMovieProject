package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.User;
import com.example.movieapp.R;
import com.royrodriguez.transitionbutton.TransitionButton;

public class LoginActivity extends Activity {
    TransitionButton btnLoginLg;
    EditText edtUsernameLg, edtPasswordLg;
    TextView txtSignUpLg;
    DBMangager dbMangager;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        edtUsernameLg = findViewById(R.id.edtUsernameLg);
        edtPasswordLg = findViewById(R.id.edtPasswordLg);
        dbMangager = new DBMangager(LoginActivity.this);

        setLogin();
        setSignUp();
    }

    public void setLogin(){
        btnLoginLg = findViewById(R.id.btnLoginLg);
        btnLoginLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = dbMangager.checkLogin(new User(edtUsernameLg.getText().toString(),edtPasswordLg.getText().toString()));

                //load animation
                btnLoginLg.startAnimation();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(status == true) {
                            status = false;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            btnLoginLg.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }else {
                            btnLoginLg.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 1000);
            }
        });
    }

    public void setSignUp(){
        txtSignUpLg = findViewById(R.id.txtSignUpLg);
        txtSignUpLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}