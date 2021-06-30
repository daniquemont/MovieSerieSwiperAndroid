package com.example.movieserieswiperandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button cLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cLogoutButton = findViewById(R.id.logoutButton);

        cLogoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

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
                        Toast.makeText(Profile.this, "Succesvol uitgelogd", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("registerError", error.toString());
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
