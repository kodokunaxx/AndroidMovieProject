package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.Adapter.SearchGridViewAdapter;
import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.HistorySearch;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;
import com.example.movieapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchActivity extends Activity {
    AutoCompleteTextView edtSearch;
    Button btnSearch;
    GridView gridView;
    DBMangager dbMangager;

    SearchGridViewAdapter searchGridViewAdapter;
    Json json = new Json();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        gridView = findViewById(R.id.gridView);
        dbMangager = new DBMangager(SearchActivity.this);
        int size = dbMangager.getAllKeyword().size();


        String[] str = new String[size - 1];
        for (int i = 0; i< size; i++){
            str[i] = dbMangager.getAllKeyword().get(i).getKeyword();
        }
        edtSearch.setAdapter(new ArrayAdapter<>(SearchActivity.this,R.layout.support_simple_spinner_dropdown_item, str));

        setSearchClick();
    }

    private void setSearchClick(){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie(edtSearch.getText().toString());

                Random random = new Random();
                int id = random.nextInt(10000);
                dbMangager.addHistorySearch(new HistorySearch(id, edtSearch.getText().toString()));
            }
        });
    }

    private void searchMovie(String keyword){
        RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/search/movie?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=en-Us&query=" + keyword + "",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieList = new ArrayList<>();
                            movieList = json.JsonMovie(response);
                            setMovieListGridView(movieList);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }

    private void setMovieListGridView(List<Movie> movieList){
        searchGridViewAdapter = new SearchGridViewAdapter(this, movieList);
        gridView.setAdapter(searchGridViewAdapter);
    }
}