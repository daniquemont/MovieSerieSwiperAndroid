package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

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

        RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/11?api_key=c90281a1d17bd06cb743a01a02f7c620", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Title: ", response.getString("original_title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
           @Override
           public void onErrorResponse(VolleyError error){
            Log.d("niet gelukt", error.getMessage());
        }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);



        recyclerViewAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}