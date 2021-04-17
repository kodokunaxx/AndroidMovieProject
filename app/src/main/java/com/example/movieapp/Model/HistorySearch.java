package com.example.movieapp.Model;

import java.io.Serializable;

public class HistorySearch implements Serializable {
    int id;
    String keyword;

    public HistorySearch() {
    }

    public HistorySearch(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
