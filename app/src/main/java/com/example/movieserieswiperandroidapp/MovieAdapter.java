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

    private Context context;
    private List<Movie> list;

    public MovieAdapter(Context context, List<Movie> list){
        this.context = context;
        this.list = list;
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
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        public TextView textTitle;

        public MovieViewHolder(View itemView){
            super(itemView);

            textTitle = itemView.findViewById(R.id.main_title);
        }
    }
}

