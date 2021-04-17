package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.User;
import com.example.movieapp.R;
import com.royrodriguez.transitionbutton.TransitionButton;

import java.util.Random;

public class SignUpActivity extends Activity {
    TransitionButton btnSignUpSU;
    EditText edtUsernameSU, edtNameSU, edtEmailSU, edtPasswordSU, edtRePasswordSU;
    CheckBox cbCheckSU;
    DBMangager dbMangager;
    boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        edtUsernameSU = findViewById(R.id.edtUsernameSU);
        edtNameSU = findViewById(R.id.edtNameSU);
        edtEmailSU = findViewById(R.id.edtEmailSU);
        edtPasswordSU = findViewById(R.id.edtPasswordSU);
        edtRePasswordSU = findViewById(R.id.edtRePasswordSU);
        cbCheckSU = findViewById(R.id.cbCheckSU);
        dbMangager = new DBMangager(SignUpActivity.this);

        setSignUp();
    }

    public void setSignUp(){
        btnSignUpSU = findViewById(R.id.btnSignUpSU);
        btnSignUpSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int id = random.nextInt(1000);
                if(TextUtils.isEmpty(edtNameSU.getText().toString()) && TextUtils.isEmpty(edtPasswordSU.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "Không để trống tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    status = false;
                }
                else if(!edtPasswordSU.getText().toString().equals(edtRePasswordSU.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Hãy xác thực lại mật khẩu!", Toast.LENGTH_SHORT).show();
                    status = false;
                }else if(cbCheckSU.isChecked() == false){
                    Toast.makeText(SignUpActivity.this, "Vui lòng chấp nhận điều khoản!", Toast.LENGTH_SHORT).show();
                    status = false;
                }else {
                    dbMangager.addUser(new User(id, edtUsernameSU.getText().toString(), edtPasswordSU.getText().toString(), edtNameSU.getText().toString(), 0, edtEmailSU.getText().toString(), "null", "null"));
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    status = true;
                }


                //load animation
                btnSignUpSU.startAnimation();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(status == true) {
                            status = false;
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                            btnSignUpSU.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }else {
                            btnSignUpSU.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 1000);

            }
        });
    }
}