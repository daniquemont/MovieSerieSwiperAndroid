package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    EditText rFullName, rEmail, rPassword;
    Button rRegisterBtn;
    TextView rLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFullName = findViewById(R.id.fullName);
        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password);
        rRegisterBtn = findViewById(R.id.loginBtn);
        rLoginBtn = findViewById(R.id.createText);

        rRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    rEmail.setError("Email is niet ingevuld");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    rPassword.setError("Password is niet ingevuld");
                    return;
                }
                if(password.length() < 6){
                    rPassword.setError("Password moet meer dan 6 karakters zijn");
                }
            }
        });
    }
}