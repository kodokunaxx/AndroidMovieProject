package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.R;
import com.royrodriguez.transitionbutton.TransitionButton;

public class ChangePasswordActivity extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    DBMangager dbMangager;
    SharedPreferences sharedpreferences;
    TextView txtUserName;
    EditText edtOldPassword, edtNewPassword, edtReNewPassword;
    TransitionButton btnChange;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_password);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        dbMangager = new DBMangager(ChangePasswordActivity.this);
        txtUserName = findViewById(R.id.txtUserName);
        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtReNewPassword = findViewById(R.id.edtReNewPassword);
        btnChange = findViewById(R.id.btnChange);

        setOnClickChange();
    }

    public void setOnClickChange(){
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPassword.getText().toString(),
                        newPass = edtNewPassword.getText().toString(),
                        reNewPass = edtReNewPassword.getText().toString();

                String  username = sharedpreferences.getString(USERNAME, "1"),
                        password = sharedpreferences.getString(PASSWORD, "1"),
                        name = sharedpreferences.getString(NAME, "1"),
                        email = sharedpreferences.getString(EMAIL, "1"),
                        gender = sharedpreferences.getString(GENDER, "1"),
                        address = sharedpreferences.getString(ADDRESS, "1");
                int id = sharedpreferences.getInt(USER_ID, 0),
                        age = sharedpreferences.getInt(AGE, 0);

                if(!oldPass.equals(password)){
                    status = false;
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu cũ không đúng!", Toast.LENGTH_SHORT).show();
                }else if(oldPass.equals(newPass)){
                    status = false;
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu mới phải khác cũ!", Toast.LENGTH_SHORT).show();
                }else if(!newPass.equals(reNewPass)) {
                    status = false;
                    Toast.makeText(ChangePasswordActivity.this, "Hãy xác thực lại mật khẩu!", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(newPass)) {
                    status = false;
                    Toast.makeText(ChangePasswordActivity.this, "Không được để trống!", Toast.LENGTH_SHORT).show();
                }else{
                    dbMangager.updateUser(id,username,newPass,name,email, age, gender, address);
                    status = true;
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(PASSWORD, newPass);
                    editor.apply();
                    Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                }

                //load animation
                btnChange.startAnimation();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(status == true) {
                            status = false;
                            Intent intent = new Intent(ChangePasswordActivity.this, UserActivity.class);
                            startActivity(intent);
                            btnChange.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }else {
                            btnChange.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 1000);
            }
        });
    }
}