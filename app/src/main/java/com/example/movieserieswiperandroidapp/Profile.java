package com.example.movieserieswiperandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    Button logoutButton;
    TextView username;
    RadioButton rbtnFilm;
    RadioButton rbtnSerie;
    RadioGroup serieOfFilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutButton = findViewById(R.id.logoutButton);
        username = findViewById(R.id.userName);
        rbtnFilm = findViewById(R.id.radioButtonFilm);
        rbtnSerie = findViewById(R.id.radioButtonSerie);
        serieOfFilms = findViewById(R.id.radioGroup);

        getUsername(username);
        serieOfFilms();

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });



    }

    public void serieOfFilms(){
        serieOfFilms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButtonFilm:
                        rbtnFilm.isChecked();
                        // variabelen aanmaken en doorsturen naar de main?
                        Log.d("checkRadio", "Jaaaaaaa");
                        break;
                    case R.id.radioButtonSerie:
                        rbtnSerie.isChecked();
                        Log.d("checkRadio", "Jaaaaaaa");
                        break;
                }
            }
        });
    }

    public void getUsername(TextView username){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/user-profile";
        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");
        Log.d("ProfileToken", token);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String responseString = response;
                        JSONObject json = null;
                        try {
                            json = new JSONObject(responseString);
                            String name = json.getString("name");
                            username.setText("Hi " + name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("usernameError", error.toString());
                        Toast.makeText(Profile.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + token);
                Log.d("checkHeader", header.toString());
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void logout(){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/logout";
        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");
        Log.d("ProfileToken", token);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("checkResponse", response);
                        Toast.makeText(Profile.this, "Successvolvoly logged out", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("logoutError", error.toString());
                        Toast.makeText(Profile.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + token);
                Log.d("checkHeader", header.toString());
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
