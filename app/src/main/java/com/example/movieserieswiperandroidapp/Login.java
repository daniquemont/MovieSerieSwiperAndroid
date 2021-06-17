package com.example.movieserieswiperandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText lEmail, lPassword;
    Button lLoginBtn;
    TextView lRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUI();
        setupListeners();
    }

    private void setupUI() {
        lEmail = findViewById(R.id.email);
        lPassword = findViewById(R.id.password);
        lLoginBtn = findViewById(R.id.loginBtn);
        lRegisterBtn = findViewById(R.id.createText);
    }

    private void setupListeners(){
        lLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkUsername();
            }
        });

        lRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, Register.class);
//                startActivity(intent);
            }
        });
    }

    void checkUsername(){
        boolean isValid = true;
        String email = lEmail.getText().toString().trim();
        String password = lPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            lEmail.setError("Voer je email in om in te kunnen loggen!");
            isValid = false;
        }else {
            if(!isEmail(lEmail)){
                lEmail.setError("Gebruik een geldige email");
                isValid = false;
            }
        }

        if(TextUtils.isEmpty(password)){
            lPassword.setError("Je moet een wachtwoord invoeren!");
            isValid = false;
        }

        if(isValid){
            if(email.equals("test@test.com") && password.equals("password")){
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                this.finish();
            }else{
                Toast.makeText(this, "Verkeerde email of wachtwoord", Toast.LENGTH_SHORT).show();
            }
        }
    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}