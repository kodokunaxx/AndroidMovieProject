package com.example.movieapp.Model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    int id;
    String poster;
    String backdrop;
    String title;
    List<MovieDetail> movieDetailList;

    public Movie() {
    }

    public Movie(int id, String poster, String backdrop, String title) {
        this.id = id;
        this.poster = poster;
        this.backdrop = backdrop;
        this.title = title;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieDetail> getMovieDetailList() {
        return movieDetailList;
    }

    public void setMovieDetailList(List<MovieDetail> movieDetailList) {
        this.movieDetailList = movieDetailList;
    }
}
