package com.example.movieapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.movieapp.Model.HistorySearch;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBMangager extends SQLiteOpenHelper {
    public DBMangager(@Nullable Context context){
        super(context, "MovieApp.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUser = "create table User(id int primary key, username varchar(255), password varchar(255), name varchar(255), email varchar(255), age int, gender varchar(255), address varchar(255))";
        String createHistorySearch = "create table HistoryUser(id int primary key, keyword varchar(255))";
        String createLoveMovie = "create table LoveMovie(id int primary key, idMovie int, poster varchar(255), backdrop varchar(255), title varchar(255), userId int)";
        String createHistoryView = "create table HistoryView(id int primary key, idMovie int, poster varchar(255), backdrop varchar(255), title varchar(255), userId int)";
        db.execSQL(createUser);
        db.execSQL(createHistorySearch);
        db.execSQL(createLoveMovie);
        db.execSQL(createHistoryView);
    }

    //region User
    public void addUser(User user){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", user.getId());
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("name", user.getName());
        contentValues.put("email", user.getEmail());
        contentValues.put("age", user.getAge());
        contentValues.put("gender", user.getGender());
        contentValues.put("address", user.getAddress());

        database.insert("User",null, contentValues);
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        String querySql = " select * from User";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(querySql, null);


        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setName(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setAge(cursor.getInt(5));
                user.setGender(cursor.getString(6));
                user.setAddress(cursor.getString(7));
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        //database.close();
        return userList;
    }

    public void updateUser(int id, String username, String password, String name, String email, int age, String gender, String address){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("age", age);
        contentValues.put("gender", gender);
        contentValues.put("address", address);

        database.update("User", contentValues,  " id = ? " , new String[]{String.valueOf(id)});
    }


    //endregion

    //region HistoryView
    public void addViewMovie(Movie movie){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        Random random = new Random();
        int id = random.nextInt(10000);
        contentValues.put("id", id);
        contentValues.put("idMovie", movie.getId());
        contentValues.put("poster", movie.getPoster());
        contentValues.put("backdrop", movie.getBackdrop());
        contentValues.put("title", movie.getTitle());
        contentValues.put("userId", movie.getUserId());

        database.insert("HistoryView",null, contentValues);
    }

    public List<Movie> getViewMovie(int idUser){
        List<Movie> movieList = new ArrayList<>();
        String query = "select * from HistoryView where userId =" + idUser /* + " group by userId,title,backdrop,poster" */ ;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToLast()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(1));
                movie.setPoster(cursor.getString(2));
                movie.setBackdrop(cursor.getString(3));
                movie.setTitle(cursor.getString(4));
                movie.setUserId(cursor.getInt(5));
                movieList.add(movie);
            } while (cursor.moveToPrevious());
        }
        cursor.close();

        return movieList;
    }
    //endregion

    //region LoveMovie
    public void addLoveMovie(Movie movie){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        Random random = new Random();
        int id = random.nextInt(10000);
        contentValues.put("id", id);
        contentValues.put("idMovie", movie.getId());
        contentValues.put("poster", movie.getPoster());
        contentValues.put("backdrop", movie.getBackdrop());
        contentValues.put("title", movie.getTitle());
        contentValues.put("userId", movie.getUserId());

        database.insert("LoveMovie",null, contentValues);
    }

    public List<Movie> getLoveMovie(int idUser){
        List<Movie> movieList = new ArrayList<>();
        String query = "select * from LoveMovie where userId =" + idUser /*+ " group by userId,title,backdrop,poster" */;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToLast()) {
            do {
                Movie movie = new Movie();
                movie.setId(cursor.getInt(1));
                movie.setPoster(cursor.getString(2));
                movie.setBackdrop(cursor.getString(3));
                movie.setTitle(cursor.getString(4));
                movie.setUserId(cursor.getInt(5));
                movieList.add(movie);
            } while (cursor.moveToPrevious());
        }
        cursor.close();

        return movieList;
    }

    public boolean checkLove(int idUser, int idMovie){
        String query = "select * from LoveMovie where userId =" + idUser + " and idMovie =" + idMovie ;
        SQLiteDatabase database = getReadableDatabase();

        try {
            Cursor cursor = database.rawQuery(query, null);
            if(cursor.getCount() > 0){
                return true;
            }
        }catch (SQLException e){}
        return false;
    }

    public void deleteLove(int idUser, int idMovie){
        SQLiteDatabase database = getReadableDatabase();
        database.execSQL("delete from LoveMovie where userId = " + idUser + " and idMovie =" +idMovie);
    }
    //endregion

    //region HistorySearch
    public void addHistorySearch(HistorySearch historySearch){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", historySearch.getId());
        contentValues.put("keyword", historySearch.getKeyword());

        database.insert("HistoryUser",null, contentValues);
    }

    public List<HistorySearch> getAllKeyword() {
        List<HistorySearch> historySearchList = new ArrayList<>();
        String querySql = " select * from HistoryUser";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(querySql, null);


        if (cursor.moveToLast()) {
            do {
                HistorySearch historySearch = new HistorySearch();
                historySearch.setKeyword(cursor.getString(1));
                historySearchList.add(historySearch);

            } while (cursor.moveToPrevious());
        }
        cursor.close();
        //database.close();
        return historySearchList;
    }
    //endregion

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
