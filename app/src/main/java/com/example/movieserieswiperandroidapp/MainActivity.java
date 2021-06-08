package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RecylerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        final Movie[] movies = new Movie[2];
        movies[0] = new Movie("Dark night", 250, "bla bla bla", 1);
        movies[1] = new Movie("Dark night 2", 350, "bla bla bla bla bla", 2);

        recyclerViewAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}