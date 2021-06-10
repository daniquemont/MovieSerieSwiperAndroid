package com.example.movieserieswiperandroidapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Movie {
    public String movieName;


    public Movie() {

    }

    public Movie(String name){
        this.movieName = name;
    }


    public String getMovieName(){return this.movieName;}
    public void setMovieName(String movieName){
        this.movieName = movieName;
    }
/*
    public int getMovieLength(){return this.movieLength;}
    public String getDescription(){return this.description;}
    public int getUuid(){return this.uuid;}*/

}
