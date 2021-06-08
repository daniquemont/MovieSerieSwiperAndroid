package com.example.movieserieswiperandroidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Movie[] movies;

    public MovieAdapter(Movie[] movies) {this.movies = movies;}

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MovieViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(v);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position){
        holder.textView.setText(movies[position].getMovieName());
    }

    @Override
    public int getItemCount(){return movies.length;}


}

