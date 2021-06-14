package com.example.movieserieswiperandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public static Object MovieViewHolder;
    private Context context;
    private List<Movie> list;

    public MovieAdapter(Context applicationContext, List<Movie> movieList) {
        this.context = applicationContext;
        this.list = movieList;
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
        Movie movie = list.get(position);

        holder.textTitle.setText(movie.getMovieName());
        holder.textDescription.setText(movie.getMovieDescription());
        holder.textLanguage.setText(movie.getMovieLanguage());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public TextView textTitle, textDescription, textLanguage;

        public MovieViewHolder(View itemView){
            super(itemView);

            textTitle = itemView.findViewById(R.id.main_title);
            textDescription = itemView.findViewById(R.id.main_description);
            textLanguage = itemView.findViewById(R.id.main_language);
        }
    }
}

