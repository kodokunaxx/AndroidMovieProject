package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movieapp.Adapter.CastHorizontalRecycleViewAdapter;
import com.example.movieapp.Adapter.MovieHorizontalRecyclerviewAdapter;
import com.example.movieapp.Database.DBMangager;
import com.example.movieapp.Json.Json;
import com.example.movieapp.Model.Cast;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;
import com.example.movieapp.Model.User;
import com.example.movieapp.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieDetailActivity extends Activity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String USER_ID = "user_id";
    SharedPreferences sharedpreferences;
    ImageView imgMovieHome, movieDetailImg, searchImgMovieDetail, imgLove;
    TextView movieDetailTitle, runTime, movieActor, movieCategory, movieEpisode, movieDate, movieOverview;
    Button btnPlay;
    String url;
    RecyclerView movieCastRecycleView, movieRelatedRecycleView;
    MovieHorizontalRecyclerviewAdapter movieHorizontalRecyclerviewAdapter;
    CastHorizontalRecycleViewAdapter castHorizontalRecycleViewAdapter;
    Json json = new Json();
    DBMangager dbMangager;
    boolean status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_movie_detail);

        imgMovieHome =findViewById(R.id.imgMovieHome);
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
        searchImgMovieDetail = findViewById(R.id.searchImgMovieDetail);
        imgLove = findViewById(R.id.imgLove);
        dbMangager = new DBMangager(MovieDetailActivity.this);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        //lấy dữ liệu từ Intent
        int iIdMovie = getIntent().getExtras().getInt("id");
        String iPoster = getIntent().getExtras().getString("poster");
        String iBackdrop = getIntent().getExtras().getString("backdrop");
        String iTitle = getIntent().getExtras().getString("title");
        int idUser = sharedpreferences.getInt(USER_ID, 0);
        status = dbMangager.checkLove(idUser,iIdMovie);

        Glide.with(this).load(iBackdrop).into(movieDetailImg);
        setMovieDetail(iIdMovie);
        viewRelatedMovie(iIdMovie);
        viewCastMovie(iIdMovie);
        this.getUrlMovieTrailer(iIdMovie);
        setImgHomeOnClick();
        setPlayOnClick(iIdMovie,iPoster,iBackdrop,iTitle, idUser);
        setSearchImgMovieDetail();
        setLoveStatus();
        setLoveOnClick(iIdMovie,iPoster,iBackdrop,iTitle, idUser);


    }

    private void setSearchImgMovieDetail(){
        searchImgMovieDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setImgHomeOnClick(){
        imgMovieHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MovieDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

//region LoveMovie
    private void setLoveStatus(){
        if(status == false){
            imgLove.setImageResource(R.drawable.like);
        }else {
            imgLove.setImageResource(R.drawable.unlike);
        }
    }

    private void setLoveOnClick(int idMovie, String poster, String backdrop, String title, int idUser){
        imgLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == false){
                    imgLove.setImageResource(R.drawable.unlike);
                    dbMangager.addLoveMovie(new Movie(idMovie, poster, backdrop, title, idUser));
                    status = true;
                }else {
                    imgLove.setImageResource(R.drawable.like);
                    dbMangager.deleteLove(idUser, idMovie);
                    status = false;
                }
            }
        });
    }
//endregion

    private void setPlayOnClick(int idMovie, String poster, String backdrop, String title, int idUser){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbMangager.addViewMovie(new Movie(idMovie, poster, backdrop, title, idUser));
                Intent intent = new Intent(MovieDetailActivity.this, PlayMovieActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    private void setMovieDetail(int idMovie){
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetailActivity.this);
        String actor[] = {"James Francis Cameron", "Michael Bay", "Christopher Nolan", "Quentin Jerome Tarantino", "Clint Eastwood Jr.", "Steven Allan Spielberg", "Heywood Woody Allen", "Frank Russell Capra", "Anh em Joe và Anthony Russo", "Peter Jackson"};
        Random random = new Random();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/" + idMovie + "?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MovieDetail movieDetail = new MovieDetail();
                        try {
                            int randomIndex = random.nextInt(actor.length);
                            movieDetail = json.JsonMovieDetail(response);
                            movieDetailTitle.setText(movieDetail.getTitle());
                            runTime.setText(movieDetail.getRuntime());
                            movieActor.setText(actor[randomIndex]);
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

    public void getUrlMovieTrailer(int idMovie){
        RequestQueue requestQueue = Volley.newRequestQueue(MovieDetailActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+ idMovie +"/videos?api_key=9ed4a1f097a3e78ed51133843d2156ea&language=vi",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            url = json.JsonMovieTrailer(response);

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