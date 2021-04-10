package com.example.movieapp.Json;

import com.example.movieapp.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Json {
    public List<Movie> JsonMovie(String jsonMovie) throws JSONException{
        List<Movie> movieList = new ArrayList<>();
        JSONObject jsonMovieObj = new JSONObject(jsonMovie);
        JSONArray jsonMovieArray = jsonMovieObj.getJSONArray("results");
        for(int i=0; i < jsonMovieArray.length(); i++){
            JSONObject jsonResultObj = jsonMovieArray.getJSONObject(i);
            movieList.add(new Movie(jsonResultObj.getInt("id"), "https://image.tmdb.org/t/p/w500/" + jsonResultObj.getString("poster_path"), "https://image.tmdb.org/t/p/w500/" + jsonResultObj.getString("backdrop_path"), jsonResultObj.getString("title")));
        }
        return movieList;
    }
}

