package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.R;

public class UserActivity extends Activity {
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
    TextView bigtxtNameUser;
    EditText txtNameUser, txtAgeUser, txtGenderUser, txtEmailUser, txtAddressUser;
    Button btnEditUser, btnEditPass, btnBack;
    DBMangager dbMangager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        dbMangager = new DBMangager(UserActivity.this);
        bigtxtNameUser = findViewById(R.id.bigtxtNameUser);
        txtNameUser = findViewById(R.id.txtNameUser);
        txtAgeUser = findViewById(R.id.txtAgeUser);
        txtGenderUser = findViewById(R.id.txtGenderUser);
        txtEmailUser = findViewById(R.id.txtEmailUser);
        txtAddressUser = findViewById(R.id.txtAddressUser);
        btnEditUser = findViewById(R.id.btnEditUser);
        btnEditPass = findViewById(R.id.btnEditPass);
        btnBack = findViewById(R.id.btnBack);

        setData();
        setOnclickChangePass();
        setOnclickChangeUser();
        setOnclickBack();
    }

    public void setData(){
        //gán dữ liệu từ shared pref
        int age = sharedpreferences.getInt(AGE, 0);
        String name = sharedpreferences.getString(NAME, "Đỗ Mạnh Hà"),
                email = sharedpreferences.getString(EMAIL, "hapro123456@gmail.com"),
                gender = sharedpreferences.getString(GENDER, "Nam"),
                address = sharedpreferences.getString(ADDRESS, "Hà Đông, Hà Nội");
        bigtxtNameUser.setText(name);
        txtNameUser.setText(name);
        txtAgeUser.setText(String.valueOf(age));
        txtGenderUser.setText(gender);
        txtEmailUser.setText(email);
        txtAddressUser.setText(address);

    }

    public void setOnclickChangePass(){
        btnEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setOnclickChangeUser(){
        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  username = sharedpreferences.getString(USERNAME, "1"),
                        password = sharedpreferences.getString(PASSWORD, "1"),
                        name = txtNameUser.getText().toString(),
                        email= txtGenderUser.getText().toString(),
                        gender = txtGenderUser.getText().toString(),
                        address = txtAddressUser.getText().toString();
                int id = sharedpreferences.getInt(USER_ID, 0),
                    age = Integer.parseInt(txtAgeUser.getText().toString());

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(NAME, name);
                editor.putInt(AGE, age);
                editor.putString(EMAIL, email);
                editor.putString(GENDER, gender);
                editor.putString(ADDRESS, address);
                editor.apply();
                dbMangager.updateUser(id,username,password,name,email, age, gender, address);
                bigtxtNameUser.setText(name);
            }
        });
    }

    public void setOnclickBack(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}