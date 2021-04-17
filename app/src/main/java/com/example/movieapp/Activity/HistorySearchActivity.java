package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.movieapp.Adapter.ListViewSearchAdapter;
import com.example.movieapp.Adapter.SearchGridViewAdapter;
import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Model.HistorySearch;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.R;

import java.util.List;

public class HistorySearchActivity extends Activity {
    ListView listHistorySearch;
    ImageView imgHistorySearch, imgsearchHistorySearch;
    DBMangager dbMangager;
    ListViewSearchAdapter listViewSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_history_search);

        listHistorySearch = findViewById(R.id.listHistorySearch);
        imgsearchHistorySearch = findViewById(R.id.imgsearchHistorySearch);
        imgHistorySearch = findViewById(R.id.imgHistorySearch);

        dbMangager = new DBMangager(HistorySearchActivity.this);

        imgHistorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorySearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        imgsearchHistorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorySearchActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        setListView();
    }

    private void setListView(){
        List<HistorySearch> historySearchList = dbMangager.getAllKeyword();
        listViewSearchAdapter = new ListViewSearchAdapter(this, historySearchList);
        listHistorySearch.setAdapter(listViewSearchAdapter);
    }
}