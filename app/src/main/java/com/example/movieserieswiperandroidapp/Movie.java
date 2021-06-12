package com.example.movieserieswiperandroidapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;


public class Movie {
    public int movieId;
    public String movieName;
    public String movieDescription;
    public String movieLanguage;
    //public int[] genreId;



    public Movie() {

    }

    public Movie(int movieId,  String movieName, String movieDescription, String movieLanguage, int[] genreId){
    //public Movie(String movieName){
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieLanguage = movieLanguage;
        //this.genreId = genreId;
    }


    public int getMovieId(){return this.movieId;}
    public void setMovieId(int movieId){ this.movieId = movieId;}

    public String getMovieName(){return this.movieName;}
    public void setMovieName(String movieName){
        this.movieName = movieName;
    }

    public String getMovieDescription() {return this.movieDescription;}
    public void setMovieDescription(String movieDescription){this.movieDescription = movieDescription;}

    public String getMovieLanguage(){return this.movieLanguage;}
    public void setMovieLanguage(String movieLanguage){this.movieLanguage = movieLanguage;}

    /*public int[] getGenreId(){return this.genreId = genreId;}
    public void setGenreId(int[] genreId){this.genreId = genreId;}*/

    /*
    public int getMovieLength(){return this.movieLength;}
    public String getDescription(){return this.description;}
    public int getUuid(){return this.uuid;}*/

}
