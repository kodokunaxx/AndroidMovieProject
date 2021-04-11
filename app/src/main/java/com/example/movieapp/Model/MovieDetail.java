package com.example.movieapp.Model;

import java.io.Serializable;

public class MovieDetail implements Serializable {
    int idMovie;
    String title; // tiêu đề
    String runtime; // thời lượng
    String imgPoster;
    String imgBackdrop;
    String tagline ;// thể loại
    String episode_number; // số tập
    String release_date; // ngày khởi chiếu
    String overview; // tóm tắt

    public MovieDetail() {
    }

    public MovieDetail(int idMovie, String title, String runtime, String imgPoster, String imgBackdrop, String tagline, String episode_number, String release_date, String overview) {
        this.idMovie = idMovie;
        this.title = title;
        this.runtime = runtime;
        this.imgPoster = imgPoster;
        this.imgBackdrop = imgBackdrop;
        this.tagline = tagline;
        this.episode_number = episode_number;
        this.release_date = release_date;
        this.overview = overview;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(String imgPoster) {
        this.imgPoster = imgPoster;
    }

    public String getImgBackdrop() {
        return imgBackdrop;
    }

    public void setImgBackdrop(String imgBackdrop) {
        this.imgBackdrop = imgBackdrop;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(String episode_number) {
        this.episode_number = episode_number;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
