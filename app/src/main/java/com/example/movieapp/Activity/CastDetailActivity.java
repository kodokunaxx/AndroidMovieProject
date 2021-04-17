package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movieapp.Adapter.MovieHorizontalRecyclerviewAdapter;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.Cast;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;
import com.example.movieapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class CastDetailActivity extends Activity {

    TextView castDetailName, castDetailDate, castDetailGender, castDetailPlace, castDetailStory;
    ImageView imgCastHome,castDetailProfile, searchImgCastDetail;
    RecyclerView castMovieJoin;
    MovieHorizontalRecyclerviewAdapter movieHorizontalRecyclerviewAdapter;

    Json json = new Json();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cast_detail);

        imgCastHome = findViewById(R.id.imgCastHome);
        castDetailName = findViewById(R.id.castDetailName);
        castDetailDate = findViewById(R.id.castDetailDate);
        castDetailGender = findViewById(R.id.castDetailGender);
        castDetailPlace = findViewById(R.id.castDetailPlace);
        castDetailStory = findViewById(R.id.castDetailStory);
        castDetailProfile = findViewById(R.id.castDetailProfile);
        castMovieJoin = findViewById(R.id.castMovieJoin);
        searchImgCastDetail = findViewById(R.id.searchImgCastDetail);

        int iIdCast = getIntent().getExtras().getInt("id");
        String iProfileImg = getIntent().getExtras().getString("profileImg");

        Glide.with(this).load(iProfileImg).into(castDetailProfile);
        setCastDetail(iIdCast);
        viewCastMovieJoin(iIdCast);
        setImgHomeOnClick();
        searchImgCastDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CastDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setImgHomeOnClick(){
        imgCastHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CastDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setCastDetail(int idCast){
        RequestQueue requestQueue = Volley.newRequestQueue(CastDetailActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/person/"+ idCast +"?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi&page=1",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Cast cast = new Cast();
                        try {
                            cast = json.JsonCastDetail(response);
                            castDetailName.setText(cast.getName());
                            castDetailDate.setText(cast.getBirthday());
                            if(cast.getGender() == 1){

                                castDetailGender.setText("Nữ");
                            }else {
                                castDetailGender.setText("Nam");
                            }
                            castDetailPlace.setText(cast.getBorn());
                            if(cast.getBiography().length() < 5){

                                castDetailStory.setText("Không có thông tin");
                            }else {
                                castDetailStory.setText(cast.getBiography());
                            }
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

    private void viewCastMovieJoin(int idCast) {
        RequestQueue requestQueue = Volley.newRequestQueue(CastDetailActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/person/"+ idCast +"/movie_credits?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JsonCastMovieJoin(response);
                            setMovieListHorizontalRecyclerviewAdapter(movieDetailList);

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

    private void setMovieListHorizontalRecyclerviewAdapter(List<Movie> movieListHorizontal) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        castMovieJoin.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(this, movieListHorizontal);
        castMovieJoin.setAdapter(movieHorizontalRecyclerviewAdapter);
    }
}