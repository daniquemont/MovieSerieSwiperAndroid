package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

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



        getData();
    }

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

