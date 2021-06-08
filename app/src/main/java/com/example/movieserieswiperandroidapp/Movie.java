package com.example.movieserieswiperandroidapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {
    @ColumnInfo
    private String movieName;

    @ColumnInfo
    private int movieLength;

    @ColumnInfo
    private String description;

    @PrimaryKey
    private int uuid;

    public Movie(String movieName, int movieLength, String description, int uuid){
        this.movieName = movieName;
        this.movieLength = movieLength;
        this.description = description;
        this.uuid = uuid;
    }

    public String getMovieName(){return this.movieName;}
    public int getMovieLength(){return this.movieLength;}
    public String getDescription(){return this.description;}
    public int getUuid(){return this.uuid;}
}
