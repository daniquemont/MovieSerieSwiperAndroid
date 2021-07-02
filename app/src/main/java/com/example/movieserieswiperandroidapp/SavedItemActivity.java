package com.example.movieserieswiperandroidapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedItemActivity extends AppCompatActivity {

    public RecyclerView sList;
    public LinearLayoutManager linearLayoutManager;
    public DividerItemDecoration dividerItemDecoration;
    public List<SavedItemsModel> savedItemsList;
    public RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_item);

        sList = findViewById(R.id.savedmovie_list);

        savedItemsList = new ArrayList<>();
        adapter = new SavedItemsAdapter(getApplicationContext(), savedItemsList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(sList.getContext(), linearLayoutManager.getOrientation());

        sList.setHasFixedSize(true);
        sList.setLayoutManager(linearLayoutManager);
        sList.addItemDecoration(dividerItemDecoration);
        sList.setAdapter(adapter);

        getSavedItems();
    }

    private void getSavedItems(){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/saved";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);

                        SavedItemsModel savedItems = new SavedItemsModel();
                        savedItems.setName(jsonObject.getString("name"));
                        savedItems.setGenre(jsonObject.getString("genre"));
                        savedItemsList.add(savedItems);
                        Log.d("response saved items", savedItemsList.toString());
                        adapter.notifyDataSetChanged();
                    }
                }catch (JSONException e) {
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
        requestQueue.add(stringRequest);

    }
}
