package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.User;
import com.example.movieapp.R;
import com.royrodriguez.transitionbutton.TransitionButton;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    SharedPreferences sharedpreferences;


    TransitionButton btnLoginLg;
    EditText edtUsernameLg, edtPasswordLg;
    TextView txtSignUpLg;
    DBMangager dbMangager;
    boolean status;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        edtUsernameLg = findViewById(R.id.edtUsernameLg);
        edtPasswordLg = findViewById(R.id.edtPasswordLg);
        dbMangager = new DBMangager(LoginActivity.this);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        //in shared prefs inside het string method we are passing key value as EMAIL_KEY and default value is
        //set to null if not present.



        setLogin();
        setSignUp();
    }

    public void setLogin(){
        btnLoginLg = findViewById(R.id.btnLoginLg);
        btnLoginLg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> userList = new ArrayList<>();
                userList = dbMangager.getAllUser();
                for (int i = 0; i < userList.size(); i++) {
                    if (edtUsernameLg.getText().toString().equals(userList.get(i).getUsername()) && edtPasswordLg.getText().toString().equals(userList.get(i).getPassword())) {
                        status = true;
                        user = userList.get(i);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(USER_ID, user.getId());
                        editor.putString(USERNAME, user.getUsername());
                        editor.putString(PASSWORD, user.getPassword());
                        editor.putString(NAME, user.getName());
                        editor.putInt(AGE, user.getAge());
                        editor.putString(EMAIL, user.getEmail());
                        editor.putString(GENDER, user.getGender());
                        editor.putString(ADDRESS, user.getAddress());
                        editor.apply();
                    }

                }

                //load animation
                btnLoginLg.startAnimation();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(status == true) {
                            status = false;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //intent.putExtra("UserObj", user);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            btnLoginLg.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }else {
                            Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
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