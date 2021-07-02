package com.example.movieserieswiperandroidapp;

public class SavedItemsModel {
    private String name;
    private String genre;
    private String description;
    private String image;

    public SavedItemsModel(){

    }

    public SavedItemsModel(String name, String genre, String description, String image){
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.image = image;
    }

    public String getName(){return name;}
    public String getGenre(){return genre;}
    public String getDescription(){return description;}
    public String getImage(){return image;}

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
