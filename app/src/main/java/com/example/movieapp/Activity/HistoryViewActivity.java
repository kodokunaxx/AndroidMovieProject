package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.movieapp.Adapter.SearchGridViewAdapter;
import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;

import java.util.List;

public class HistoryViewActivity extends Activity {
    GridView gridHistoryView;
    ImageView imgHistoryView, imgsearchHistoryView;
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_ID = "user_id";
    SharedPreferences sharedpreferences;
    SearchGridViewAdapter searchGridViewAdapter;
    DBMangager dbMangager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history_view);

        gridHistoryView = findViewById(R.id.gridHistoryView);
        imgHistoryView = findViewById(R.id.imgHistoryView);
        imgsearchHistoryView = findViewById(R.id.imgsearchHistoryView);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        dbMangager = new DBMangager(HistoryViewActivity.this);

        imgHistoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryViewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imgsearchHistoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryViewActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        setMovieListGridView();
    }

    private void setMovieListGridView(){
        int idUser = sharedpreferences.getInt(USER_ID, 0);
        List<Movie> movieList = dbMangager.getViewMovie(idUser);
        searchGridViewAdapter = new SearchGridViewAdapter(this, movieList);
        gridHistoryView.setAdapter(searchGridViewAdapter);
    }
}