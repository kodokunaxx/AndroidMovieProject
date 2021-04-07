package com.example.movieapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.movieapp.Model.User;

public class DBMangager extends SQLiteOpenHelper {
    public DBMangager(@Nullable Context context){
        super(context, "MovieApp.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser = "create table User(id int primary key, username varchar(255), password varchar(255), name varchar(255), email varchar(255), age int, gender varchar(255), address varchar(255))";
        db.execSQL(createUser);
    }

    public void addUser(User user){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", user.getId());
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("email", user.getEmail());
        contentValues.put("name", user.getName());
        contentValues.put("age", user.getAge());
        contentValues.put("gender", user.getGender());
        contentValues.put("address", user.getAddress());

        database.insert("User",null, contentValues);
    }

    public boolean checkLogin(User user){
        String querySql = " select * from User where username = " + user.getUsername()+ " and password = " + user.getPassword();
        SQLiteDatabase database = getWritableDatabase();
        try {
            Cursor cursor = database.rawQuery(querySql, null);
            if(cursor.getCount() > 0){
                return true;
            }
        }catch (SQLException e){

        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
