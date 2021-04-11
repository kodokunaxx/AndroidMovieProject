package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movieapp.Adapter.CastHorizontalRecycleViewAdapter;
import com.example.movieapp.Adapter.MovieHorizontalRecyclerviewAdapter;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.Cast;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;
import com.example.movieapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends Activity {
    ImageView movieDetailImg;
    TextView movieDetailTitle, runTime, movieActor, movieCategory, movieEpisode, movieDate, movieOverview;
    Button btnPlay;
    RecyclerView movieCastRecycleView, movieRelatedRecycleView;
    MovieHorizontalRecyclerviewAdapter movieHorizontalRecyclerviewAdapter;
    CastHorizontalRecycleViewAdapter castHorizontalRecycleViewAdapter;

    Json json = new Json();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_detail);

        movieDetailImg = findViewById(R.id.movieDetailImg);
        movieDetailTitle = findViewById(R.id.movieDetailTitle);
        runTime = findViewById(R.id.runTime);
        movieActor = findViewById(R.id.movieActor);
        movieCategory = findViewById(R.id.movieCategory);
        movieEpisode = findViewById(R.id.movieEpisode);
        movieDate = findViewById(R.id.movieDate);
        movieOverview = findViewById(R.id.movieOverview);
        btnPlay = findViewById(R.id.btnPlay);
        movieCastRecycleView = findViewById(R.id.movieCastRecycleView);
        movieRelatedRecycleView = findViewById(R.id.movieRelatedRecycleView);

        //lấy dữ liệu từ Intent
        int iIdMovie = getIntent().getExtras().getInt("id");
        String iBackdrop = getIntent().getExtras().getString("backdrop");

        Glide.with(this).load(iBackdrop).into(movieDetailImg);
        setMovieDetail(iIdMovie);
        viewRelatedMovie(iIdMovie);
        viewCastMovie(iIdMovie);
    }

    private void setMovieDetail(int idMovie){
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetailActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + idMovie + "?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MovieDetail movieDetail = new MovieDetail();
                        try {
                            movieDetail = json.JsonMovieDetail(response);
                            movieDetailTitle.setText(movieDetail.getTitle());
                            runTime.setText(movieDetail.getRuntime());
                            movieCategory.setText(movieDetail.getTagline());
                            movieEpisode.setText(movieDetail.getEpisode_number());
                            movieDate.setText(movieDetail.getRelease_date());
                            movieOverview.setText(movieDetail.getOverview());
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

    private void viewRelatedMovie(int idMovie) {
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetailActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+ idMovie +"/similar?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi-VN&page=1",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Movie> movieDetailList = new ArrayList<>();
                            movieDetailList = json.JsonMovie(response);
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

    private void viewCastMovie(int idMovie){
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetailActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+ idMovie +"/credits?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Cast> castList = new ArrayList<>();
                            castList = json.JsonCast(response);
                            setCastListHorizontalRecyclerviewAdapter(castList);

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
        movieRelatedRecycleView.setLayoutManager(layoutManager);
        movieHorizontalRecyclerviewAdapter = new MovieHorizontalRecyclerviewAdapter(this, movieListHorizontal);
        movieRelatedRecycleView.setAdapter(movieHorizontalRecyclerviewAdapter);
    }

    private void setCastListHorizontalRecyclerviewAdapter(List<Cast> castListHorizontal) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        movieCastRecycleView.setLayoutManager(layoutManager);
        castHorizontalRecycleViewAdapter = new CastHorizontalRecycleViewAdapter(this, castListHorizontal);
        movieCastRecycleView.setAdapter(castHorizontalRecycleViewAdapter);
    }
}