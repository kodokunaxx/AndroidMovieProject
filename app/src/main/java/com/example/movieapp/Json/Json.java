package com.example.movieapp.Json;

import com.example.movieapp.Model.Cast;
import com.example.movieapp.Model.Movie;
import com.example.movieapp.Model.MovieDetail;

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

    public MovieDetail JsonMovieDetail(String jsonMovieDetail) throws JSONException{
        MovieDetail movieDetail;
        JSONObject jsonMovieObj = new JSONObject(jsonMovieDetail);
        String genres = "";
        JSONArray jsonArray = jsonMovieObj.getJSONArray("genres");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonGenres = jsonArray.getJSONObject(i);
            if (i == jsonArray.length() - 1) {
                genres = genres + jsonGenres.getString("name");
            } else {
                genres = genres + jsonGenres.getString("name") + ",";
            }
        }

        movieDetail = new MovieDetail(jsonMovieObj.getInt("id"),jsonMovieObj.getString("title"),jsonMovieObj.getString("runtime"),jsonMovieObj.getString("poster_path"),jsonMovieObj.getString("backdrop_path"),genres,"1",jsonMovieObj.getString("release_date"),jsonMovieObj.getString("overview"));
        return  movieDetail;
    }

    public List<Movie> JsonCastMovieJoin(String jsonMovie) throws JSONException{
        List<Movie> movieList = new ArrayList<>();
        JSONObject jsonMovieObj = new JSONObject(jsonMovie);
        JSONArray jsonMovieArray = jsonMovieObj.getJSONArray("cast");
        for(int i=0; i < jsonMovieArray.length(); i++){
            JSONObject jsonResultObj = jsonMovieArray.getJSONObject(i);
            movieList.add(new Movie(jsonResultObj.getInt("id"), "https://image.tmdb.org/t/p/w500/" + jsonResultObj.getString("poster_path"), "https://image.tmdb.org/t/p/w500/" + jsonResultObj.getString("backdrop_path"), jsonResultObj.getString("title")));
        }
        return movieList;
    }

    public List<Cast> JsonCast(String jsonCast) throws JSONException{
        List<Cast> castList = new ArrayList<>();
        JSONObject jsonCastObj = new JSONObject(jsonCast);
        JSONArray jsonCastArray = jsonCastObj.getJSONArray("cast");
        for(int i=0; i < jsonCastArray.length(); i++){
            JSONObject jsonResultObj = jsonCastArray.getJSONObject(i);
            castList.add(new Cast(jsonResultObj.getInt("id"), jsonResultObj.getString("name"), "https://image.tmdb.org/t/p/w500/" + jsonResultObj.getString("profile_path")));
        }
        return castList;
    }

    public Cast JsonCastDetail(String jsonCastDetail) throws JSONException{
        Cast cast;
        JSONObject jsonCastObj = new JSONObject(jsonCastDetail);
        cast = new Cast(jsonCastObj.getInt("id"),jsonCastObj.getString("name"),jsonCastObj.getString("birthday"),jsonCastObj.getInt("gender"),jsonCastObj.getString("place_of_birth"), jsonCastObj.getString("biography"),jsonCastObj.getString("profile_path"));
        return  cast;
    }

}

