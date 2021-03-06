package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CardStackLayoutManager manager;
    public CardStackAdapter adapter;
    public List<ItemModel> items = new ArrayList<>();
    public List<SavedItemsModel> savedItems = new ArrayList<>();
    public List<String> addedMoviesForSwipe = new ArrayList<>();
    public List<String> moviesSwipedRight = new ArrayList<>();
    public int Index = 0;
    public String movieName;
    public String movieGenre;
    public String movieDescription;
    public String movieImage;

    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = findViewById(R.id.profileButton);
        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");
        Log.d("ProfileToken", token);

        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });


        CardStackView cardStackView = findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(MainActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                    //moviesSwipedRight.add(addedMoviesForSwipe.toString());
                    //Log.d("saved list", moviesSwipedRight.toString());
                    //addedMoviesForSwipe.remove(Index);
                    addItemToList(movieName, movieGenre);
                }
                if (direction == Direction.Top){
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(MainActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                    //addedMoviesForSwipe.remove(Index);
                    //addedMoviesForSwipe.remove(Index);
                    //.d("deleted movies", addedMoviesForSwipe.toString());
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }
            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.movie_name);
                TextView genreMovie = view.findViewById(R.id.movie_genre);
                ImageView movieImageUrl = view.findViewById(R.id.movie_image);
                //View movieNameView = view.findViewById(R.id.movie_name);
                movieName = String.valueOf(tv.getText());
                movieGenre = String.valueOf(genreMovie.getText());
                //movieImage = String.valueOf(movieImageUrl.get);

                Log.d("Movie added", movieName + ' ' + movieGenre);
                Log.d(TAG, "onCardAppeared: " + position + ", movie name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.movie_name);
                Log.d(TAG, "onCardAppeared: " + position + ", movie name: " + tv.getText());


            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    //Hier worden de films ingeladen vanaf de database
    private List<ItemModel> addList() {
        String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Movieurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("swiped movies", addedMoviesForSwipe.toString());
                        try{
                            JSONArray array = new JSONArray(response);

                            for(int i = 0; i < array.length(); i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                ItemModel item = new ItemModel();

                                item.setMovieName(jsonObject.getString("movieName"));
                                item.setGenre(jsonObject.getString("genre"));
                                item.setMovieImage(jsonObject.getString("movieImageUrl"));
                                items.add(item);
                                adapter.notifyDataSetChanged();
                                Log.d("List data", items.toString());
                                Log.d("movie response", response);
                            }
                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("movie error", error.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return items;
    }

    //hier worden de items die naar rechts worden geswiped naar de saveditems gestuurd.
    public void addItemToList(String movieName, String movieGenre){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/saveditems";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("check for post response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error bij post", error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put("name", movieName);
                params.put("genre", movieGenre);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}