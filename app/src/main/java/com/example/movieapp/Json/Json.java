package com.example.movieapp.Json;

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

    public MovieDetail JsonTVShowDetail(String jsonMovieDetail) throws JSONException{
        MovieDetail movieDetail;
        JSONObject jsonMovieObj = new JSONObject(jsonMovieDetail);
        movieDetail = new MovieDetail(jsonMovieObj.getInt("id"),jsonMovieObj.getString("name"),jsonMovieObj.getString("episode_run_time"),jsonMovieObj.getString("poster_path"),jsonMovieObj.getString("backdrop_path"),jsonMovieObj.getString("genres[1].name"),jsonMovieObj.getString("number_of_episodes"),jsonMovieObj.getString("first_air_date"),jsonMovieObj.getString("overview"));
        return  movieDetail;
    }

//    public CategoryItem JSONTvDetail(String jsontvshow) throws JSONException {
//        CategoryItem categoryItem;
//        JSONObject jsonObject = new JSONObject(jsontvshow);
//        String genres = "";
//        JSONArray jsonArray = jsonObject.getJSONArray("genres");
//
//        String vote = jsonObject.getString("vote_average") + "/" + jsonObject.getString("vote_count");
//        //// lấy thể loại phim///
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject job = jsonArray.getJSONObject(i);
//            if (i == jsonArray.length() - 1) {
//                genres = genres + job.getString("name");
//            } else {
//                genres = genres + job.getString("name") + ",";
//            }
//        }
//        categoryItem = new CategoryItem(jsonObject.getString("episode_run_time"), jsonObject.getString("first_air_date"), jsonObject.getString("overview"), genres, vote, jsonObject.getString("name"));
//
//
//        return categoryItem;
//    }
}

