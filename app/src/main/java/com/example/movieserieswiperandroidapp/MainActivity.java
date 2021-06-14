package com.example.movieserieswiperandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Movie> movieList;
    private RecyclerView.Adapter adapter;

    private String APIurl = "https://api.themoviedb.org/3/movie/popular?api_key=c90281a1d17bd06cb743a01a02f7c620&language=en-US";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.main_list);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(getApplicationContext(), movieList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(),
                linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mList);

        getData();
    }

    Movie deletedMovie = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            List<Movie> archivedMovie = new ArrayList<>();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedMovie = movieList.get(position);
                    movieList.remove(position);
                    adapter.notifyItemRemoved(position);
                    /*Snackbar.make(mList, deletedMovie, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener(){
                                @Override
                                public void onClick(View view){
                                    movieList.add(position, deletedMovie);
                                    adapter.notifyItemInserted(position);
                                }
                            }).show();*/
                    break;

                case ItemTouchHelper.RIGHT:
                    final Movie movieName = movieList.get(position);
                    archivedMovie.add(movieName);
                    movieList.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    private void getData(){

        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        Request jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                APIurl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            //String movieName = (String) response.get("original_title");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Movie movie = new Movie();
                               // movie.setMovieId(jsonObject.getInt("id"));
                                movie.setMovieName(jsonObject.getString("original_title"));
                                movie.setMovieDescription(jsonObject.getString("overview"));
                                movie.setMovieLanguage(jsonObject.getString("original_language"));
                               // movie.setGenreId(jsonObject.getInt("genre_ids"));
                                movieList.add(movie);
                                adapter.notifyDataSetChanged();
                            }


                               /* Movie movie = new Movie();
                                movie.setMovieName(movieName);
                                movieList.add(movie);
                                adapter.notifyDataSetChanged();*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("Volley", error.toString());
                    }
                });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);


        }

}

