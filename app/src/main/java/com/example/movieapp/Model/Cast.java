package com.example.movieapp.Model;

public class Cast {
    int id;
    String name;
    String birthday;
    int gender;
    String born;
    String biography;
    String profieImg;

    public Cast() {
    }

    public Cast(int id, String name, String profieImg) {
        this.id = id;
        this.name = name;
        this.profieImg = profieImg;
    }

    public Cast(int id, String name, String birthday, int gender, String born, String biography, String profieImg) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.born = born;
        this.biography = biography;
        this.profieImg = profieImg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getBiography() { return biography; }

    public void setBiography(String biography) { this.biography = biography; }

    public String getProfieImg() {
        return profieImg;
    }

    public void setProfieImg(String profieImg) {
        this.profieImg = profieImg;
    }
}
